package chat.rs.services;

import chat.rs.dtos.MessageInChatDTO;
import chat.rs.model.MessageInChat;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageAssembler {

    public MessageInChat assemblePostFromPostDTO(final MessageInChatDTO messageInChatDTO, String ipAddress) {
        MessageInChat messageInChat = new MessageInChat();

        messageInChat.setMessage(messageInChatDTO.getMessage());
        messageInChat.setUsername(messageInChatDTO.getUsername());
        messageInChat.setIpAddress(ipAddress);

        //todo appropriate date handler
        messageInChat.setMessageDate(null);

        return messageInChat;
    }

    public MessageInChatDTO assemblePostDTOFromPost(final MessageInChat messageInChat) {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();

        messageInChatDTO.setUsername(messageInChat.getUsername());
        messageInChatDTO.setMessage(messageInChat.getMessage());
        messageInChatDTO.setIpAddress(messageInChat.getIpAddress());
        messageInChatDTO.setIpAddress(messageInChat.getIpAddress());

        //todo appropriate date handler
        messageInChatDTO.setFormattedDate(null);

        return messageInChatDTO;
    }

    public List<MessageInChatDTO> assemblePostDTOSFromPosts(List<MessageInChat> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        } else {
            List<MessageInChatDTO> messageInChatDTOS = new ArrayList<>();
            messages.stream().forEach(message -> messageInChatDTOS.add(assemblePostDTOFromPost(message)));
            return messageInChatDTOS;
        }
    }
}
