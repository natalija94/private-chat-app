package chat.rs.service.impl;

import chat.rs.chatenum.ChatMessageFilterMapper;
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
import chat.rs.service.DiscussionHandler;
import chat.rs.util.PageableFactory;
import chat.rs.util.RestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author natalija
 */
@Service
@Transactional
@Slf4j
public class DiscussionHandlerImpl implements DiscussionHandler {
    /**
     * Discussion repository bean.
     */
    private final DiscussionRepository discussionRepository;

    /**
     * ChatMessageConverter bean.
     */
    private final ChatMessageConverter chatMessageConverter;

    /**
     * SimpMessagingTemplate messagingTemplate.
     */
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Constructor injection.
     *
     * @param discussionRepository as DiscussionRepository
     * @param chatMessageConverter as chatMessageConverter
     * @param messagingTemplate    as messagingTemplate
     */
    public DiscussionHandlerImpl(DiscussionRepository discussionRepository, ChatMessageConverter chatMessageConverter, SimpMessagingTemplate messagingTemplate) {
        this.discussionRepository = discussionRepository;
        this.chatMessageConverter = chatMessageConverter;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Concrete implementation on sendMessage functionality.
     * Here it is specified to format message and save it to DB but also send complete message list to .
     *
     * @param messageDTO as message to be saved.
     * @param ipAddress  of the server that requested message sending.
     * @return an information whether its message is appropriate and successfully saved.
     */
    public ResponseDTO saveMessage(MessageInChatDTO messageDTO, String ipAddress) {
        ResponseDTO dto = new ResponseDTO();
        MessageInChat messageInChat = chatMessageConverter.assembleMessageFromMessageDTO(messageDTO, ipAddress);

        try {
            discussionRepository.save(messageInChat);
            if (ChatMessageState.OKAY == messageInChat.getState()) {
                dto.setStatus(ResponseStatus.SUCCESS);
                log.info("Message is successfully saved. Details: {}", messageInChat);
            } else {
                dto.setStatus(ResponseStatus.ERROR);
                dto.setErrorMessage("Hey!:) Offensive content is not appropriate in this discussion.");
            }
            dto.setData(messageInChat.getState());
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("ERROR. Not possible to send message. Please try again");
        }

        //inform other clients regarding new messages
        messagingTemplate.convertAndSend(RestConstants.FULL_DISCUSSION_PATH, getFullConversation(DiscussionFilter.NONE));

        //handle response to sender of the message over the rest
        return dto;
    }

    /**
     * Returns paginated message view objects from DB. These results are formatted so it can be easily previewed on some UI.
     *
     * @param pageInfoDTO      information about page.
     * @param discussionFilter as DiscussionFilter.
     * @return ResponseDTO.
     */
    public ResponseDTO getConversationDetails(PageInfoDTO pageInfoDTO, DiscussionFilter discussionFilter) {
        ResponseDTO dto = new ResponseDTO();
        try {
            Slice<MessageInChatVO> messagesForSpecifiedPage;
            ChatMessageState query = ChatMessageFilterMapper.mapDiscussionFilterToChatMessageState(discussionFilter);
            if (query == null) {
                messagesForSpecifiedPage = discussionRepository.findConversationPaginated(PageableFactory.pageableInstance(pageInfoDTO));
            } else {
                messagesForSpecifiedPage = discussionRepository.findFullConversationByStatePaginated(query, PageableFactory.pageableInstance(pageInfoDTO));
            }
            dto.setData(messagesForSpecifiedPage.getContent());
            dto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("SERVER ERROR. Getting conversation details...");
        }
        return dto;
    }

    /**
     * Returns full list of message view objects from DB. These results are formatted so it can be easily previewed on some UI.
     *
     * @param discussionFilter as DiscussionFilter.
     * @return ResponseDTO.
     */
    public ResponseDTO getFullConversation(DiscussionFilter discussionFilter) {
        ResponseDTO dto = new ResponseDTO();
        try {
            List<MessageInChatVO> messagesForSpecifiedPage;
            ChatMessageState query = ChatMessageFilterMapper.mapDiscussionFilterToChatMessageState(discussionFilter);
            if (query == null) {
                messagesForSpecifiedPage = discussionRepository.getFullConversationForPreview();
            } else {
                messagesForSpecifiedPage = discussionRepository.findFullConversationByState(query);
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
