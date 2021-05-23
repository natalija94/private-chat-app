package chat.rs.service.impl;

import chat.rs.service.MessageInspector;
import chat.rs.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageInspectorImpl implements MessageInspector {
    @Override
    public boolean isMessageAppropriate(String message) {
        //just an example of service; it could be called another entity here (different logic, http request etc..)
        // which checks the content of the message
        return StringUtils.containsIgnoreCase(message, Constants.HATE_SPEECH);
    }
}