package chat.rs.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DiscussionPageDTO implements Serializable {
    public DiscussionPageDTO(){}

    public DiscussionPageDTO(List<MessageInChatDTO> messageInChatDTOS){
        this.messageInChatDTOS = messageInChatDTOS;
    }

    private List<MessageInChatDTO> messageInChatDTOS;
}