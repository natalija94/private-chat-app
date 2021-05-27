package chat.rs.service;

/**
 * @author natalija
 */
public interface MessageInspector {
    /**
     * Service for checking whether message content is appropriate. The idea in real world would be
     * to call some external service (i.e.: over RestHTTP in order to check the value).
     *
     * @param message to be checked
     * @return whether is offensive or not.
     */
    boolean isTextMessageOffensive(String message);
}
