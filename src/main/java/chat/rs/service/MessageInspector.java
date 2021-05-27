package chat.rs.service;

/**
 * @author natalija
 */
public interface MessageInspector {
    boolean isMessageOffensive(String message);
}
