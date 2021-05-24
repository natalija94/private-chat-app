package chat.rs.repository;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.model.MessageInChat;
import chat.rs.model.MessageInChatVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageInChat, Long> {
    @Query("SELECT new chat.rs.model.MessageInChatVO(m) FROM MessageInChat m WHERE :state = m.state")
    Slice<MessageInChatVO> findAllByState(ChatMessageState state, Pageable pageable);

}