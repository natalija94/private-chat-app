package chat.rs.service;

public interface MessageInspector {
    boolean isMessageOffensive(String message);
}
