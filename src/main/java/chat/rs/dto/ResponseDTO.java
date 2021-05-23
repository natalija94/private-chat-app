package chat.rs.dto;

import chat.rs.chatenum.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {
    private ResponseStatus status;
    private Object data;
    private String errorMessage;
}
