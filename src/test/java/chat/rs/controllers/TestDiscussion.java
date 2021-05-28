package chat.rs.controllers;

import chat.rs.chatenum.DiscussionFilter;
import chat.rs.controller.DiscussionFacade;
import chat.rs.dto.MessageInChatDTO;
import chat.rs.mocks.MockDTOs;
import chat.rs.service.impl.DiscussionHandlerImpl;
import chat.rs.util.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author natalija
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DiscussionFacade.class)
public class TestDiscussion {

    @Autowired
    private MockMvc mvc;

    @MockBean
    DiscussionHandlerImpl discussionHandlerImpl;

    @Test
    public void getFullDiscussion() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.FULL_DISCUSSION_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getFullDiscussionFiltered() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.FULL_DISCUSSION_PATH)
                .param("filter", DiscussionFilter.OFFENSIVE_CONTENT.getValue())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPaginatedDiscussion() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.PART_OF_DISCUSSION_PATH)
                .param("filter", DiscussionFilter.OFFENSIVE_CONTENT.getValue())
                .param("page", "1")
                .param("numberOfMessagesPerPage", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPaginatedDiscussionBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(RestConstants.DISCUSSION_GLOBAL_PATH + RestConstants.PART_OF_DISCUSSION_PATH)
                .param("filter", DiscussionFilter.OFFENSIVE_CONTENT.getValue())
                .param("page", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void sendMessageExpectedToBeFine() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/discuss/send-message")
                .content(asJsonString(MockDTOs.createMessageWithFullOffensiveData()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}