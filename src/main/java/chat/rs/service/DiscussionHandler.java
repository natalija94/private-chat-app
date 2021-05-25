package chat.rs.service;

import chat.rs.chatenum.DiscussionFilter;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.dto.ResponseDTO;
import chat.rs.chatenum.ChatMessageState;
import chat.rs.chatenum.ResponseStatus;
import chat.rs.converter.ChatMessageConverter;
import chat.rs.model.MessageInChat;
import chat.rs.model.MessageInChatVO;
import chat.rs.repository.DiscussionRepository;
import chat.rs.util.PageableFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DiscussionHandler {
    private final DiscussionRepository discussionRepository;
    private final ChatMessageConverter chatMessageConverter;

    public DiscussionHandler(DiscussionRepository discussionRepository, ChatMessageConverter chatMessageConverter) {
        this.discussionRepository = discussionRepository;
        this.chatMessageConverter = chatMessageConverter;
    }

    public ResponseDTO sendMessage(MessageInChatDTO postDTO, String ipAddress) {
        ResponseDTO dto = new ResponseDTO();
        MessageInChat messageInChat = chatMessageConverter.assembleMessageFromMessageDTO(postDTO, ipAddress);

        try {
            discussionRepository.save(messageInChat);
            if (ChatMessageState.OKAY == messageInChat.getState()) {
                dto.setStatus(ResponseStatus.SUCCESS);
                log.info("Message is successfully saved. Details: {}", messageInChat);
            } else {
                dto.setStatus(ResponseStatus.ERROR);
                dto.setErrorMessage("Hey!:) Offensive content is not appropriate in this discussion.");
            }
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("ERROR. Not possible to send message. Please try again");
        }
        return dto;
    }

    public ResponseDTO getConversationDetails(PageInfoDTO pageInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        try {
            Slice<MessageInChatVO> messagesForSpecifiedPage = discussionRepository.findFullConversationByState(ChatMessageState.OKAY,
                    PageableFactory.pageableInstance(pageInfoDTO));

            dto.setData(messagesForSpecifiedPage.getContent());
            dto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("SERVER ERROR. Getting conversation details...");
        }
        return dto;
    }

    public ResponseDTO getFullConversation(DiscussionFilter discussionFilter) {
        ResponseDTO dto = new ResponseDTO();
        try {
            List<MessageInChatVO> messagesForSpecifiedPage;
            switch (discussionFilter) {
                case APPROPRIATE_CONTENT:
                    messagesForSpecifiedPage = discussionRepository.findFullConversationByState(ChatMessageState.OKAY);
                    break;

                case OFFENSIVE_CONTENT:
                    messagesForSpecifiedPage = discussionRepository.findFullConversationByState(ChatMessageState.OFFENSIVE);
                    break;

                case NONE:
                default:
                    messagesForSpecifiedPage = discussionRepository.getFullConversationForPreview();
            }

            dto.setData(messagesForSpecifiedPage);
            dto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("SERVER ERROR. Getting conversation details...");
        }
        return dto;
    }
}
