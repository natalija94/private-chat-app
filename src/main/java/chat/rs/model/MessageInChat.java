package chat.rs.model;

import chat.rs.chatenum.ChatMessageState;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author natalija
 */
@Entity
@Table
@Data
public class MessageInChat {
    public MessageInChat() {
    }

    public MessageInChat(MessageInChat messageInChat) {
        this.ipAddress = messageInChat.getIpAddress();
        this.id = messageInChat.getId();
        this.username = messageInChat.getUsername();
        this.message = messageInChat.getMessage();
        this.messageDate = messageInChat.getMessageDate();
        this.state = messageInChat.getState();
    }

    /**
     * Message id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    /**
     * Sender's username.
     */
    @Column(nullable = false)
    private String username;

    /**
     * Text message content.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    /**
     * IP address of the machine where message is sent.
     * Introduced due to handling offensive content, and eventually lately the sender ban.
     */
    @Column
    private String ipAddress;

    /**
     * Date and time when the message is sent.
     */
    @Column(nullable = false)
    private LocalDateTime messageDate;

    /**
     * Chat message state. Good for filtering, some post histories, etc...
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatMessageState state = ChatMessageState.OKAY;
}
