package chat.rs.services;

import chat.rs.dtos.MessageInChatDTO;
import chat.rs.dtos.PageInfoDTO;
import chat.rs.dtos.ResponseDTO;
import chat.rs.enums.ChatMessageState;
import chat.rs.enums.ResponseStatus;
import chat.rs.model.MessageInChat;
import chat.rs.repository.MessageRepository;
import chat.rs.util.PageableFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DiscussionHandler {
    private final MessageRepository messageRepository;
    private final ChatMessageAssembler chatMessageAssembler;

    public DiscussionHandler(MessageRepository messageRepository, ChatMessageAssembler chatMessageAssembler) {
        this.messageRepository = messageRepository;
        this.chatMessageAssembler = chatMessageAssembler;
    }

    public ResponseDTO sendMessage(MessageInChatDTO postDTO, String ipAddress) {
        ResponseDTO dto = new ResponseDTO();
        MessageInChat messageInChat = chatMessageAssembler.assemblePostFromPostDTO(postDTO, ipAddress);

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

    public ResponseDTO getConversationDetails(PageInfoDTO pageInfoDTO) {
        ResponseDTO dto = new ResponseDTO();
        try {
            List<MessageInChatDTO> messageInChatDTOS = chatMessageAssembler.assemblePostDTOSFromPosts(
                    messageRepository.findAllByStateOrderByPostDateAsc(ChatMessageState.OKAY, PageableFactory.pageableInstance(pageInfoDTO)));
            dto.setData(messageInChatDTOS);
            dto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setStatus(ResponseStatus.ERROR);
            dto.setErrorMessage("SERVER ERROR. Getting conversation details...");
        }
        return dto;
    }
}
