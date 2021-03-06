package chat.rs.chatenum;

/**
 * @author natalija
 */
public class ChatMessageFilterMapper {
    /**
     * Utility class. Private constructor.
     */
    private ChatMessageFilterMapper() {
    }

    /**
     * Convert discussion filter into appropriate chat message DB column/query.
     *
     * @param discussionFilter as filter
     * @return DB message state.
     */
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
