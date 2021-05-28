package chat.rs.mocks;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.model.MessageInChat;
import chat.rs.model.MessageInChatVO;
import chat.rs.util.DateUtil;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author natalija
 */
public class MockViewObjects {
    private MockViewObjects() {
    }

    public static MessageInChatVO createMessageVOWithAnyContent(ChatMessageState state) {
        MessageInChatVO messageInChat = new MessageInChatVO();
        messageInChat.setMessage("This is a message!");
        messageInChat.setMessageDate("2021-05-27T20:08:08.418");
        messageInChat.setUsername("Natalija!");
        messageInChat.setState(state);
        return messageInChat;
    }

    public static MessageInChatVO createMessageVOWithAnyContent2(ChatMessageState state) {
        MessageInChatVO messageInChat = new MessageInChatVO();
        messageInChat.setMessage("One more!");
        messageInChat.setMessageDate("2021-05-27T20:08:08.418");
        messageInChat.setUsername("Pera!");
        messageInChat.setState(state);
        return messageInChat;
    }

    public static Slice<MessageInChatVO> createMultipleVOs() {
        Slice<MessageInChatVO> slice = new SliceImpl<>(createListOfMessagesVOs());
        return slice;
    }

    public static List<MessageInChatVO> createListOfMessagesVOs() {
        MessageInChatVO messageInChatVO1 = createMessageVOWithAnyContent(ChatMessageState.OKAY);
        MessageInChatVO messageInChatVO2 = createMessageVOWithAnyContent(ChatMessageState.OKAY);
        MessageInChatVO messageInChatVO3 = createMessageVOWithAnyContent2(ChatMessageState.OFFENSIVE);
        return List.of(messageInChatVO1, messageInChatVO2, messageInChatVO3);
    }
}
