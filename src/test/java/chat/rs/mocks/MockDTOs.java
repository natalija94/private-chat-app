package chat.rs.mocks;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.model.MessageInChat;

import static chat.rs.util.Constants.HATE_SPEECH;

/**
 * @author natalija
 */
public class MockDTOs {
    private MockDTOs() {
    }

    public static MessageInChatDTO createMessageWithFullOkayData() {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();
        messageInChatDTO.setMessage("This is a message!");
        messageInChatDTO.setUsername("Natalija!");
        return messageInChatDTO;
    }

    public static MessageInChatDTO createMessageWithFullOffensiveData() {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();
        messageInChatDTO.setMessage(HATE_SPEECH);
        messageInChatDTO.setUsername("Natalija!");
        return messageInChatDTO;
    }

    public static MessageInChatDTO createMessageWithoutTextMessage() {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();
        messageInChatDTO.setUsername("Natalija!");
        return messageInChatDTO;
    }

    public static MessageInChatDTO createMessageWithoutUsername() {
        MessageInChatDTO messageInChatDTO = new MessageInChatDTO();
        messageInChatDTO.setMessage("This is a message!");
        return messageInChatDTO;
    }

    public static MessageInChatDTO nullMessageDTO() {
        return null;
    }

    //page info
    public static PageInfoDTO createPageInfoOkay() {
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setPage(1);
        pageInfoDTO.setPage(20);

        return pageInfoDTO;
    }

    //page info
    public static PageInfoDTO createWrongPageInfoData() {
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setPage(-1);
        pageInfoDTO.setPage(20);
        return pageInfoDTO;
    }
}
