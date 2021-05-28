package chat.rs.services;

import chat.rs.chatenum.ChatMessageState;
import chat.rs.chatenum.DiscussionFilter;
import chat.rs.chatenum.ResponseStatus;
import chat.rs.converter.ChatMessageConverter;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.ResponseDTO;
import chat.rs.mocks.MockDBObjects;
import chat.rs.mocks.MockDTOs;
import chat.rs.mocks.MockViewObjects;
import chat.rs.model.MessageInChat;
import chat.rs.model.MessageInChatVO;
import chat.rs.repository.DiscussionRepository;
import chat.rs.service.impl.DiscussionHandlerImpl;
import chat.rs.service.impl.MessageInspectorImpl;
import chat.rs.util.Constants;
import chat.rs.util.PageableFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Slice;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static chat.rs.mocks.MockDBObjects.ANY_IP_ADDRESS;

/**
 * @author natalija
 */
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class TestDiscussionHandler {

    @Mock
    private DiscussionRepository repository;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private ChatMessageConverter chatMessageConverter;

    @Mock
    MessageInspectorImpl messageInspector;

    @InjectMocks
    DiscussionHandlerImpl discussionHandler;

    @BeforeTestMethod
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveNullDTOMessageResponse() {
        Mockito.when(repository.save(null)).thenReturn(null);
        ResponseDTO dto = discussionHandler.saveMessage(MockDTOs.createMessageWithFullOkayData(), ANY_IP_ADDRESS);
        Assert.assertTrue(dto.getStatus() == ResponseStatus.ERROR);
        Assert.assertTrue(dto.getErrorMessage() != null);
    }

    @Test
    public void testSaveCorruptedDTOMessageResponse() {
        MessageInChat corruptedMessage = MockDBObjects.createMessageDBWithoutState();
        Mockito.lenient().when(repository.save(corruptedMessage)).thenReturn(corruptedMessage);
        ResponseDTO dto = discussionHandler.saveMessage(MockDTOs.createMessageWithFullOkayData(), ANY_IP_ADDRESS);
        Assert.assertTrue(dto.getStatus() == ResponseStatus.ERROR);
        Assert.assertTrue(dto.getErrorMessage() != null);
    }

    @Test
    public void testSaveOffensiveMessageResponse() {
        MessageInChatDTO messageInChatDTO = MockDTOs.createMessageWithFullOkayData();
        MessageInChat messageInChat = MockDBObjects.createMessageDBInChat(ChatMessageState.OKAY);
        Mockito.when(chatMessageConverter.assembleMessageFromMessageDTO(messageInChatDTO, ANY_IP_ADDRESS)).thenReturn(messageInChat);
        Mockito.when(repository.save(messageInChat)).thenReturn(messageInChat);

        ResponseDTO dto = discussionHandler.saveMessage(messageInChatDTO, ANY_IP_ADDRESS);
        Assert.assertTrue(dto.getStatus() == ResponseStatus.SUCCESS);
        Assert.assertTrue(dto.getErrorMessage() == null);
        Assert.assertEquals(dto.getData(), messageInChat.getState());
    }

    @Test
    public void testSaveOkayMessageResponse() {
        MessageInChatDTO messageInChatDTO = MockDTOs.createMessageWithFullOffensiveData();
        MessageInChat messageInChat = MockDBObjects.createMessageDBInChat(ChatMessageState.OFFENSIVE);
        Mockito.when(chatMessageConverter.assembleMessageFromMessageDTO(messageInChatDTO, ANY_IP_ADDRESS)).thenReturn(messageInChat);
        Mockito.when(repository.save(messageInChat)).thenReturn(messageInChat);

        ResponseDTO dto = discussionHandler.saveMessage(messageInChatDTO, ANY_IP_ADDRESS);
        Assert.assertTrue(dto.getStatus() == ResponseStatus.ERROR);
        Assert.assertEquals(dto.getErrorMessage(), Constants.HATE_SPEECH_MESSAGE);
        Assert.assertEquals(dto.getData(), messageInChat.getState());
    }

    @Test
    public void testFindFullConversationWithoutPagination() {
        Slice<MessageInChatVO> messageInChatVOS = MockViewObjects.createMultipleVOs();
        Mockito.when(repository.getFullConversation()).thenReturn(messageInChatVOS.getContent());

        ResponseDTO dto = discussionHandler.getFullConversation(DiscussionFilter.NONE);

        Assert.assertTrue(dto.getStatus() == ResponseStatus.SUCCESS);
        Assert.assertTrue(dto.getErrorMessage() == null);
        Assert.assertTrue(dto.getData() != null);
    }

    @Test
    public void testFindConversationWithPaginationAppropriateContent() {
        Slice<MessageInChatVO> messageInChatVOS = MockViewObjects.createMultipleVOs();
        Mockito.lenient().when(repository.findFullConversationByState(ChatMessageState.OKAY)).thenReturn(messageInChatVOS.getContent());
        Mockito.when(repository.findConversationByStatePaginated(ChatMessageState.OKAY, PageableFactory.pageableInstance(null))).thenReturn(messageInChatVOS);

        ResponseDTO dto = discussionHandler.getConversationDetails(MockDTOs.createPageInfoOkay(), DiscussionFilter.APPROPRIATE_CONTENT);

        Assert.assertTrue(dto.getStatus() == ResponseStatus.SUCCESS);
        Assert.assertTrue(dto.getErrorMessage() == null);
        Assert.assertTrue(dto.getData() != null);
    }

    @Test
    public void testResponseWhenNullResultsFromDB() {
        Mockito.lenient().when(repository.findFullConversationByState(ChatMessageState.OKAY)).thenReturn(null);
        Mockito.when(repository.findConversationByStatePaginated(ChatMessageState.OKAY, PageableFactory.pageableInstance(null))).thenReturn(null);

        ResponseDTO dto = discussionHandler.getConversationDetails(MockDTOs.createPageInfoOkay(), DiscussionFilter.APPROPRIATE_CONTENT);

        Assert.assertTrue(dto.getStatus() == ResponseStatus.SUCCESS);
        Assert.assertTrue(dto.getErrorMessage() == null);
    }
}