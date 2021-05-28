package chat.rs.repository;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.model.MessageInChat;
import chat.rs.model.MessageInChatVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author natalija
 */
public interface DiscussionRepository extends CrudRepository<MessageInChat, Long> {
    /**
     * Provides pagintaed result of chat messages filtered by state.
     *
     * @param state    as filter.
     * @param pageable for number of results and its page.
     * @return view objects.
     */
    @Query("SELECT new chat.rs.model.MessageInChatVO(m) FROM MessageInChat m WHERE :state = m.state")
    Slice<MessageInChatVO> findConversationByStatePaginated(ChatMessageState state, Pageable pageable);

    /**
     * Provides paginated result of chat messages unfiltered.
     *
     * @param pageable for number of results and its page.
     * @return view objects.
     */
    @Query("SELECT new chat.rs.model.MessageInChatVO(m) FROM MessageInChat m")
    Slice<MessageInChatVO> findConversationPaginated(Pageable pageable);

    /**
     * Provides unmounted list of chat messages unfiltered filtered by state.
     *
     * @param state as filter.
     * @return view objects.
     */
    @Query("SELECT new chat.rs.model.MessageInChatVO(m) FROM MessageInChat m WHERE :state = m.state")
    List<MessageInChatVO> findFullConversationByState(ChatMessageState state);

    /**
     * Provides complete list of chatMessages.
     *
     * @return view objects.
     */
    @Query("SELECT new chat.rs.model.MessageInChatVO(m) FROM MessageInChat m")
    List<MessageInChatVO> getFullConversation();
}