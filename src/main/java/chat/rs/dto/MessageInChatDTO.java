package chat.rs.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author natalija
 */
@Data
public class MessageInChatDTO implements Serializable {
    /**
     * Sender's username.
     */
    private String username;

    /**
     * Sender's message.
     */
    private String message;

    /**
     * Sender's ipAddress.
     */
    private String messageDate;
}
