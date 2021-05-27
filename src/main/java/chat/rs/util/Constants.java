package chat.rs.util;

/**
 * @author natalija
 */
public class Constants {
    /**
     * Util class. Private constructor.
     */
    private Constants() {
    }

    /**
     * Constant regarding pagination, default number of messages per page.
     */
    public static int DEFAULT_NUMBER_OF_MESSAGES_PER_REQUEST = 100;
    /**
     * Constant regarding pagination, default page number.
     */
    public static int DEFAULT_PAGE_NUMBER = 0;

    /**
     * Mock example for kind of hate speech.
     * My idea was: conversation should be filtered so it doesn't contain any hate speech.
     */
    public static String HATE_SPEECH = "This is a hate speech regarding some topic.";
}
