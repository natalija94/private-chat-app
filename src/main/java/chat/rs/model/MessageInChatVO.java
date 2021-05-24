package chat.rs.model;

import chat.rs.util.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageInChatVO {
    private String username;
    private String message;
    private String messageDate;

    //copy constructor
    public MessageInChatVO(MessageInChat messageInChat) {
        this.username = messageInChat.getUsername();
        this.message = messageInChat.getMessage();
        this.messageDate = DateUtil.convertToDateString(messageInChat.getMessageDate());
    }
}
