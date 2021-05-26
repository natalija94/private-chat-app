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

@Service
public class ChatMessageConverter {

    private final MessageInspector inspector;

    public ChatMessageConverter(MessageInspector inspector) {
        this.inspector = inspector;
    }

    public MessageInChat assembleMessageFromMessageDTO(final MessageInChatDTO messageInChatDTO, String ipAddress) {
        MessageInChat messageInChat = new MessageInChat();

        messageInChat.setMessage(messageInChatDTO.getMessage());
        messageInChat.setUsername(messageInChatDTO.getUsername());
        messageInChat.setIpAddress(ipAddress);

        if (inspector.isMessageOffensive(messageInChatDTO.getMessage())) {
            messageInChat.setState(ChatMessageState.OFFENSIVE);
        }

        messageInChat.setMessageDate(DateUtil.convertToLocalDateTimeNow());

        return messageInChat;
    }

    public MessageInChatDTO assembleMessageDTOFromMessage(final MessageInChat messageInChat) {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();

        messageInChatDTO.setUsername(messageInChat.getUsername());
        messageInChatDTO.setMessage(messageInChat.getMessage());

        messageInChatDTO.setMessageDate(DateUtil.convertToDateString(messageInChat.getMessageDate()));

        return messageInChatDTO;
    }

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
