package chat.rs.model;

import chat.rs.chatenum.ChatMessageState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class MessageInChat {
    @Id
    //todo : handle sequence
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String username;
    private String message;

    //for handling offensive content
    private String ipAddress;

    private LocalDateTime messageDate;

    private ChatMessageState state = ChatMessageState.OKAY;
}
