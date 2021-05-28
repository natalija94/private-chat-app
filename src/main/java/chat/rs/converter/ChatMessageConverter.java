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
        if (messageInChatDTO == null) {
            return null;
        }
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

}
