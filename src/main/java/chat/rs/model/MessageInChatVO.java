package chat.rs.model;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.util.DateUtil;
import lombok.Data;

/**
 * @author natalija
 */
@Data
public class MessageInChatVO {
    /**
     * Username property.
     */
    private String username;

    /**
     * Sender's username.
     */
    private String message;

    /**
     * Message's date.
     */
    private String messageDate;

    /**
     * Message's state.
     */
    private ChatMessageState state;

    /**
     * Copy constructor of messageInChat preapre MessageInChatVO as view object;
     *
     * @param messageInChat
     */
    public MessageInChatVO(MessageInChat messageInChat) {
        this.username = messageInChat.getUsername();
        this.message = messageInChat.getMessage();
        this.state = messageInChat.getState();
        this.messageDate = DateUtil.convertToDateString(messageInChat.getMessageDate());
    }

    /**
     * No args constructor.
     */
    public MessageInChatVO(){

    }
}
