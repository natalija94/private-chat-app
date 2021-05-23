package chat.rs.controller;

import chat.rs.dtos.MessageInChatDTO;
import chat.rs.dtos.PageInfoDTO;
import chat.rs.dtos.ResponseDTO;
import chat.rs.services.DiscussionHandler;
import chat.rs.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class DiscussionFacade {

    //TODO: consider changing to websocket mechanism...
    private final DiscussionHandler discussionHandler;

    public DiscussionFacade(DiscussionHandler discussionHandler) {
        this.discussionHandler = discussionHandler;
    }

    @PostMapping("/send-message")
    public ResponseDTO postSynchronizationData(@RequestBody MessageInChatDTO synchronizationRequestTO, HttpServletRequest httpRequest) {
        log.info("New message received! Message info: ", synchronizationRequestTO);
        return discussionHandler.sendMessage(synchronizationRequestTO, HttpRequestUtil.getClientIpAddressFromRequest(httpRequest));
    }

    @GetMapping("/get-conversation-data")
    public ResponseDTO getConversationData(@RequestParam("page") int page,
                                                 @RequestParam("numberOfMessagesPerPage") int numberOfMessagesPerPage) {
        return discussionHandler.getConversationDetails(new PageInfoDTO(page, numberOfMessagesPerPage));
    }
}
