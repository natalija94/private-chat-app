package chat.rs.service;

import chat.rs.chatenum.DiscussionFilter;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.dto.ResponseDTO;

/**
 * @author natalija
 */
public interface DiscussionHandler {
    /**
     * Method which purpose is to save received message.
     *
     * @param messageDTO as message to be saved.
     * @param ipAddress  of the server that requested message sending.
     * @return responseDTO regarding success/error while saving to some storage.
     */
    ResponseDTO saveMessage(MessageInChatDTO messageDTO, String ipAddress);

    /**
     * Returns Paginated (not full) filtered conversation details.
     *
     * @param pageInfoDTO      information about page.
     * @param discussionFilter as DiscussionFilter.
     * @return conversation details.
     */
    ResponseDTO getConversationDetails(PageInfoDTO pageInfoDTO, DiscussionFilter discussionFilter);

    /**
     * Returns complete (but possibly filtered) conversation details.
     * Not recommended to return full conversation at once.
     * (getConversationDetails method should be used instead in general)
     *
     * @param discussionFilter information about page.
     * @return conversation details.
     */
    ResponseDTO getFullConversation(DiscussionFilter discussionFilter);
}
