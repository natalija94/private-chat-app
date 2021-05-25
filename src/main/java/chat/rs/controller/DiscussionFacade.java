package chat.rs.controller;

import chat.rs.chatenum.DiscussionFilter;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.dto.ResponseDTO;
import chat.rs.service.DiscussionHandler;
import chat.rs.util.HttpRequestUtil;
import chat.rs.util.RestConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(RestConstants.DISCUSSION_GLOBAL_PATH)
public class DiscussionFacade {

    //TODO: consider implementing websocket mechanism instead of HTTP get part of discussion...
    private final DiscussionHandler discussionHandler;

    public DiscussionFacade(DiscussionHandler discussionHandler) {
        this.discussionHandler = discussionHandler;
    }

    @PostMapping(RestConstants.SEND_MESSAGE_PATH)
    public ResponseDTO postSynchronizationData(@RequestBody MessageInChatDTO synchronizationRequestTO, HttpServletRequest httpRequest) {
        log.info("New message received! Message info: {}", synchronizationRequestTO);
        return discussionHandler.sendMessage(synchronizationRequestTO, HttpRequestUtil.getClientIpAddressFromRequest(httpRequest));
    }

    @GetMapping(RestConstants.GET_PART_OF_DISCUSSION_PATH)
    public ResponseDTO getConversationData(@RequestParam(name = "page", required = false) int page,
                                           @RequestParam(name = "numberOfMessagesPerPage", required = false) int numberOfMessagesPerPage) {
        log.info("Get resource: {}", RestConstants.GET_PART_OF_DISCUSSION_PATH);
        return discussionHandler.getConversationDetails(new PageInfoDTO(page, numberOfMessagesPerPage));
    }

    @GetMapping(RestConstants.GET_FULL_DISCUSSION_PATH)
    public ResponseDTO getFullDiscussion(@RequestParam(name = "filter") DiscussionFilter filter) {
        log.info("Get resource: {}", RestConstants.GET_PART_OF_DISCUSSION_PATH);
        return discussionHandler.getFullConversation(filter);
    }
}
