package chat.rs.util;

/**
 * @author natalija
 */
public class RestConstants {
    /**
     * Util class. Private constructor.
     */
    private RestConstants() {

    }

    /**
     * Global Discussion Rest controller path and .
     */
    public static final String DISCUSSION_GLOBAL_PATH = "/discuss";

    /**
     * Constant for Websocket synchronization destination.
     */
    public static final String SYNCHRONIZE = "/synchronize";

    /**
     * Constant for Rest & Websocket destination for "get paginated discussion".
     */
    public static final String PART_OF_DISCUSSION_PATH = "/discussion-part";

    /**
     * Constant for Rest & Websocket destination "get full discussion".
     */
    public static final String FULL_DISCUSSION_PATH = "/full-discussion";

    /**
     * Constant for Rest post destination send message.
     */
    public static final String SEND_MESSAGE_PATH = "/send-message";
}
