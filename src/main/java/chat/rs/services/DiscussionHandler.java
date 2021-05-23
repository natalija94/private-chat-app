package chat.rs.services;

import chat.rs.dtos.MessageInChatDTO;
import chat.rs.dtos.PageInfoDTO;
import chat.rs.enums.ChatMessageState;
import chat.rs.repository.MessageRepository;
import chat.rs.util.Constants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionHandler {
    final MessageRepository messageRepository;
    final ChatMessageAssembler chatMessageAssembler;

    public DiscussionHandler(MessageRepository messageRepository, ChatMessageAssembler chatMessageAssembler) {
        this.messageRepository = messageRepository;
        this.chatMessageAssembler = chatMessageAssembler;
    }

    public void sendMessage(MessageInChatDTO postDTO, String ipAddress) {
        messageRepository.save(chatMessageAssembler.assemblePostFromPostDTO(postDTO, ipAddress));
    }

    public List<MessageInChatDTO> getRequestedAmountOfPosts(PageInfoDTO pageInfoDTO) {
        Pageable pageable = PageRequest.of(pageInfoDTO.getPage(), pageInfoDTO.getNumberOfPostsPerPage() > 0 ? pageInfoDTO.getNumberOfPostsPerPage() : Constants.DEFAULT_NUMBER_OF_MESSAGES_PER_REQUEST);

        return chatMessageAssembler.assemblePostDTOSFromPosts(
                messageRepository.findAllByStateOrderByPostDateAsc(ChatMessageState.OKAY, pageable));
    }

}
