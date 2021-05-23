package chat.rs.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class MessageInChatDTO implements Serializable {
    private String username;
    private String message;
    private String ipAddress;
    private PageInfoDTO pageInfoDTO;

    private String formattedDate;
}
