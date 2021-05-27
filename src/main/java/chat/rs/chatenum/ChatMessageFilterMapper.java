package chat.rs.chatenum;

/**
 * @author natalija
 */
public class ChatMessageFilterMapper {
    private ChatMessageFilterMapper() {
    }

    public static ChatMessageState mapDiscussionFilterToChatMessageState(DiscussionFilter discussionFilter) {
        switch (discussionFilter) {
            case APPROPRIATE_CONTENT:
                return ChatMessageState.OKAY;
            case OFFENSIVE_CONTENT:
                return ChatMessageState.OFFENSIVE;
            default:
                return null;
        }
    }
}
