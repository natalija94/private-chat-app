package chat.rs.converter;

import chat.rs.dto.MessageInChatDTO;
import chat.rs.chatenum.ChatMessageState;
import chat.rs.model.MessageInChat;
import chat.rs.service.MessageInspector;
import chat.rs.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author natalija
 */
@Service
public class ChatMessageConverter {

    /**
     * MessageInspector Bean.
     */
    private final MessageInspector inspector;

    /**
     * Constructor injection.
     *
     * @param inspector for MessageInspector.
     */
    public ChatMessageConverter(MessageInspector inspector) {
        this.inspector = inspector;
    }

    /**
     * Assembles Message DB object from Message DTO object and sender ipAddress.
     *
     * @param messageInChatDTO as dto.
     * @param ipAddress        ipAddress of the sender.
     * @return DB object.
     */
    public MessageInChat assembleMessageFromMessageDTO(final MessageInChatDTO messageInChatDTO, String ipAddress) {
        MessageInChat messageInChat = new MessageInChat();
        messageInChat.setMessage(messageInChatDTO.getMessage());
        messageInChat.setUsername(messageInChatDTO.getUsername());
        messageInChat.setIpAddress(ipAddress);
        if (inspector.isTextMessageOffensive(messageInChatDTO.getMessage())) {
            messageInChat.setState(ChatMessageState.OFFENSIVE);
        }
        messageInChat.setMessageDate(DateUtil.convertToLocalDateTimeNow());
        return messageInChat;
    }

    /**
     * Assembles Message DTO object from Message DB object.
     *
     * @param messageInChat as DB object.
     * @return DTO object.
     */
    public MessageInChatDTO assembleMessageDTOFromMessage(final MessageInChat messageInChat) {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();
        messageInChatDTO.setUsername(messageInChat.getUsername());
        messageInChatDTO.setMessage(messageInChat.getMessage());
        messageInChatDTO.setMessageDate(DateUtil.convertToDateString(messageInChat.getMessageDate()));
        return messageInChatDTO;
    }

    /**
     * Assembles Message DTOs object from Message DBs object.
     *
     * @param messages as list of DB object.
     * @return list of DTO object.
     */
    public List<MessageInChatDTO> assemblePostDTOSFromMessage(List<MessageInChat> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        } else {
            List<MessageInChatDTO> messageInChatDTOS = new ArrayList<>();
            messages.stream().forEach(message -> messageInChatDTOS.add(assembleMessageDTOFromMessage(message)));
            return messageInChatDTOS;
        }
    }
}
