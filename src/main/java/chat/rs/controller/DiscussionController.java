package chat.rs.controller;

import chat.rs.dtos.DiscussionPageDTO;
import chat.rs.dtos.MessageInChatDTO;
import chat.rs.dtos.PageInfoDTO;
import chat.rs.services.DiscussionHandler;
import chat.rs.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class DiscussionController {

    //TODO: consider changing to websocket mechanism...
    final DiscussionHandler discussionHandler;

    public DiscussionController(DiscussionHandler discussionHandler) {
        this.discussionHandler = discussionHandler;
    }

    @PostMapping("/send-message")
    public DiscussionPageDTO postSynchronizationData(@RequestBody MessageInChatDTO synchronizationRequestTO, HttpServletRequest httpRequest) {
        discussionHandler.sendMessage(synchronizationRequestTO, HttpRequestUtil.getClientIpAddressFromRequest(httpRequest));
        return new DiscussionPageDTO(discussionHandler.getRequestedAmountOfPosts(synchronizationRequestTO.getPageInfoDTO()));
    }

    @GetMapping("/get-conversation-data")
    public DiscussionPageDTO getConversationData(@RequestParam("page") int page,
                                                 @RequestParam("numberOfMessagesPerPage") int numberOfMessagesPerPage) {
        return new DiscussionPageDTO(discussionHandler.getRequestedAmountOfPosts(new PageInfoDTO(page, numberOfMessagesPerPage)));
    }
}
