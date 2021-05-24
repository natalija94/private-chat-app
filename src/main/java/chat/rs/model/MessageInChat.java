package chat.rs.model;

import chat.rs.chatenum.ChatMessageState;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class MessageInChat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String username;

    @Column
    private String message;

    //for handling offensive content, and eventually do the chatter ban
    @Column
    private String ipAddress;

    @Column
    private LocalDateTime messageDate;

    @Column
    @Enumerated(EnumType.STRING)
    private ChatMessageState state = ChatMessageState.OKAY;
}
