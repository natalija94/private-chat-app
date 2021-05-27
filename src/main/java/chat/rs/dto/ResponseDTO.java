package chat.rs.dto;

import chat.rs.chatenum.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author natalija
 */
@Data
public class ResponseDTO implements Serializable {
    /**
     * Property response status of the response.
     */
    private ResponseStatus status;

    /**
     * Property result data.
     */
    private Object data;

    /**
     * Details regarding error which occurred.
     */
    private String errorMessage;
}
