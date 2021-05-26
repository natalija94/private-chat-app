package chat.rs.model;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.util.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageInChatVO {
    private String username;
    private String message;
    private String messageDate;
    private ChatMessageState state;

    //copy constructor
    public MessageInChatVO(MessageInChat messageInChat) {
        this.username = messageInChat.getUsername();
        this.message = messageInChat.getMessage();
        this.state = messageInChat.getState();
        this.messageDate = DateUtil.convertToDateString(messageInChat.getMessageDate());
    }
}
