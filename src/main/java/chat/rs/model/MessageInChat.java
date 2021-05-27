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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    //for handling offensive content, and eventually do the chatter ban
    @Column
    private String ipAddress;

    @Column(nullable = false)
    private LocalDateTime messageDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChatMessageState state = ChatMessageState.OKAY;
}
