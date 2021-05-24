package chat.rs.service;

import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.dto.ResponseDTO;
import chat.rs.chatenum.ChatMessageState;
import chat.rs.chatenum.ResponseStatus;
import chat.rs.converter.ChatMessageConverter;
import chat.rs.model.MessageInChat;
import chat.rs.repository.MessageRepository;
import chat.rs.util.PageableFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DiscussionHandler {
    private final MessageRepository messageRepository;
    private final ChatMessageConverter chatMessageConverter;

    public DiscussionHandler(MessageRepository messageRepository, ChatMessageConverter chatMessageConverter) {
        this.messageRepository = messageRepository;
        this.chatMessageConverter = chatMessageConverter;
    }

    @Transactional
    public ResponseDTO sendMessage(MessageInChatDTO postDTO, String ipAddress) {
        ResponseDTO dto = new ResponseDTO();
        MessageInChat messageInChat = chatMessageConverter.assemblePostFromPostDTO(postDTO, ipAddress);

        try {
            if (ChatMessageState.OKAY == messageInChat.getState()) {
                messageRepository.save(messageInChat);
                dto.setStatus(ResponseStatus.SUCCESS);
                log.info("Message is successfully saved. Details: ", messageInChat);
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

    @Transactional
    public ResponseDTO getConversationDetails(PageInfoDTO pageInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        try {
            List<MessageInChatDTO> messageInChatDTOS = chatMessageConverter.assemblePostDTOSFromPosts(
                    messageRepository.findAllByStateOrderByMessageDateAsc(ChatMessageState.OKAY, PageableFactory.pageableInstance(pageInfoDTO)));
            dto.setData(messageInChatDTOS);
            dto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("SERVER ERROR. Getting conversation details...");
        }
        return dto;
    }
}
