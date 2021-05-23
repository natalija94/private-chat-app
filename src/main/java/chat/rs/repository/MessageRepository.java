package chat.rs.repository;

import chat.rs.enums.ChatMessageState;
import chat.rs.model.MessageInChat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageInChat, Long> {
    List<MessageInChat> findAllByStateOrderByPostDateAsc(ChatMessageState state, Pageable pageable);
}