package chat.rs.dtos;

import chat.rs.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {
    private ResponseStatus status;
    private Object data;
    private String errorMessage;
}
