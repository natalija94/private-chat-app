package chat.rs.converters;

import chat.rs.chatenum.ChatMessageFilterMapper;
import chat.rs.chatenum.ChatMessageState;
import chat.rs.chatenum.DiscussionFilter;
import chat.rs.converter.ChatMessageConverter;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.dto.PageInfoDTO;
import chat.rs.mocks.MockDBObjects;
import chat.rs.mocks.MockDTOs;
import chat.rs.model.MessageInChat;
import chat.rs.service.MessageInspector;
import chat.rs.service.impl.MessageInspectorImpl;
import chat.rs.util.Constants;
import chat.rs.util.PageableFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author natalija
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TestConvertersAndBusinessLogic {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public MessageInspector messageInspector() {
            return new MessageInspectorImpl() {
            };
        }

        @Bean
        public ChatMessageConverter messageConverter() {
            return new ChatMessageConverter(messageInspector()) {
            };
        }
    }

    @Autowired
    private ChatMessageConverter messageConverter;

    @Autowired
    private MessageInspector messageInspector;

    @Test
    public void checkMessageInspectorJob() {
        boolean isOffensive1 = messageInspector.isTextMessageOffensive(Constants.HATE_SPEECH);
        boolean isOffensive2 = messageInspector.isTextMessageOffensive("No hate speech.");
        Assert.assertTrue(isOffensive1 && !isOffensive2);
    }

    @Test
    public void checkConversionToMessageInDB() {
        MessageInChatDTO messageInChatDTO = MockDTOs.createMessageWithFullOffensiveData();
        MessageInChat messageInChat = messageConverter.assembleMessageFromMessageDTO(messageInChatDTO, MockDBObjects.ANY_IP_ADDRESS);
        Assert.assertEquals(messageInChat.getMessage(), messageInChatDTO.getMessage());
        Assert.assertEquals(messageInChat.getUsername(), messageInChatDTO.getUsername());
        Assert.assertNotEquals(messageInChat.getState(), ChatMessageState.OKAY);
        Assert.assertEquals(messageInChat.getIpAddress(), MockDBObjects.ANY_IP_ADDRESS);
    }

    @Test
    public void checkNullMessageDTO() {
        MockDTOs.nullMessageDTO();
        MessageInChatDTO messageInChatDTO = MockDTOs.nullMessageDTO();
        MessageInChat messageInChat = messageConverter.assembleMessageFromMessageDTO(messageInChatDTO, MockDBObjects.ANY_IP_ADDRESS);
        Assert.assertNull(messageInChat);
    }

    @Test
    public void checkMapperDiscussionFilterToMessageStateQuery() {
        Assert.assertNull(ChatMessageFilterMapper.mapDiscussionFilterToChatMessageState(DiscussionFilter.NONE));
        Assert.assertTrue(ChatMessageFilterMapper.mapDiscussionFilterToChatMessageState(DiscussionFilter.OFFENSIVE_CONTENT) == ChatMessageState.OFFENSIVE);
        Assert.assertTrue(ChatMessageFilterMapper.mapDiscussionFilterToChatMessageState(DiscussionFilter.APPROPRIATE_CONTENT) == ChatMessageState.OKAY);
    }

    @Test
    public void checkPreparationForPagination() {
        PageInfoDTO pageInfoDTO = MockDTOs.createWrongPageInfoData();
        Pageable request = PageableFactory.pageableInstance(pageInfoDTO);

        Assert.assertTrue(request.getPageSize() == Constants.DEFAULT_NUMBER_OF_MESSAGES_PER_REQUEST);
        Assert.assertTrue(request.getPageNumber() == Constants.DEFAULT_PAGE_NUMBER);
    }
}