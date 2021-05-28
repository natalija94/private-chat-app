package chat.rs.mocks;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.model.MessageInChat;
import org.junit.platform.commons.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author natalija
 */
public class MockDBObjects {
    private MockDBObjects() {
    }

    public static MessageInChat createNullMessage() {
        return null;
    }

    public static final String ANY_IP_ADDRESS = "111.111.111.111";

    public static MessageInChat createMessageDBWithoutState() {
        MessageInChat messageInChat = new MessageInChat();
        messageInChat.setMessage("This is a message!");
        messageInChat.setMessageDate(LocalDateTime.now());
        messageInChat.setId(((Double) (Math.random() * 10000)).longValue());
        messageInChat.setUsername("Natalija!");
        messageInChat.setState(ChatMessageState.OKAY);
        messageInChat.setIpAddress("111.111.111.111");
        return messageInChat;
    }

    public static MessageInChat createMessageDBInChat(ChatMessageState state) {
        MessageInChat messageInChat = createMessageDBWithoutState();
        messageInChat.setState(state);
        return messageInChat;
    }

    public static List<MessageInChat> createListOfMessagesInChat() {
        MessageInChat m1 = createMessageDBInChat(ChatMessageState.OKAY);
        MessageInChat m2 = createMessageDBInChat(ChatMessageState.OKAY);
        MessageInChat m3 = createMessageDBInChat(ChatMessageState.OFFENSIVE);
        MessageInChat m4 = createMessageDBInChat(ChatMessageState.OKAY);
        MessageInChat m5 = createMessageDBInChat(ChatMessageState.OKAY);
        List<MessageInChat> messages = Arrays.asList(m1, m2, m3, m4, m5);
        return messages;
    }
}
