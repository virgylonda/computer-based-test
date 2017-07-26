package pji.cbt.rest.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Answer;

import pji.cbt.entities.Question;
import pji.cbt.rest.controller.AnswerRestController;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;
import pji.cbt.util.CORSFilter;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class AnswerRestTest {
	
private MockMvc mockMvc;
	
	 private Question questionAnswer;
	
	 @Mock
	 private AnswerService ansService;
	 
	 @Mock
	 private QuestionService quesService;
	    
	 
	 @InjectMocks
	 private AnswerRestController answerController;
	 
		 
	 @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(answerController)
	                .addFilters(new CORSFilter())
	                .build();
	    }
	 
	
	    /**
	     * Test GetAllAnswer
	     */
	 
	    @Test
	    public void test_get_all_answer() throws Exception {
	    	List<Answer> answer = Arrays.asList(
	    			new Answer(1, questionAnswer, 1, "The answer", true));
	    			

	        when(ansService.findAllAnswer()).thenReturn(answer);

	        mockMvc.perform(get("/answer/getallanswer"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(1)))
	                .andExpect(jsonPath("$[0].idAnswer", is(1)))
	                .andExpect(jsonPath("$[0].questionAnswer", is(questionAnswer)))
	                .andExpect(jsonPath("$[0].orderingAnswer", is(1)))
	                .andExpect(jsonPath("$[0].answer", is("The answer")))
	                .andExpect(jsonPath("$[0].correctAnswer", is(true)));

	        verify(ansService, times(1)).findAllAnswer();
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test getAnswer by ID
	     */
	    @Test
	    public void test_get_answer_by_id() throws Exception {
	        Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);
	        
	        when(ansService.findOne(1)).thenReturn(answer);

	        mockMvc.perform(get("/answer/detail/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.idAnswer", is(1)))
	                .andExpect(jsonPath("$.questionAnswer", is(questionAnswer)))
	                .andExpect(jsonPath("$.orderingAnswer", is(1)))
	                .andExpect(jsonPath("$.answer", is("The answer is this")))
	                .andExpect(jsonPath("$.correctAnswer", is(false)));
	        		
	        verify(ansService, times(1)).findOne(1);
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test getAnswer by ID fail 404 not found
	     */
	    
	    @Test
	    public void test_get_answer_by_id_fail_404_not_found() throws Exception {
	        when(ansService.findOne(1)).thenReturn(null);

	        mockMvc.perform(get("/answer/detail/{id}", 1))
	                .andExpect(status().isNotFound());

	        verify(ansService, times(1)).findOne(1);
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test create new Answer
	     */
	    @Test
	    public void test_create_answer_success() throws Exception {
	        Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);

	        when(ansService.answerExists(answer)).thenReturn(false);
	        doNothing().when(ansService).createAnswer(answer);

	        mockMvc.perform(
	                post("/answer/createanswer")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(answer)))
	                .andExpect(status().isCreated())
	                .andExpect(header().string("location", containsString("/answer/detail/1")));

	        verify(ansService, times(1)).answerExists(refEq(answer));
	        verify(ansService, times(1)).createAnswer(refEq(answer));
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test delete answer
	     */
	    @Test
	    public void test_delete_answer_success() throws Exception {
	    	Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);

	        when(ansService.findOne(answer.getIdAnswer())).thenReturn(answer);
	        doNothing().when(ansService).deleteAnswer(answer.getIdAnswer());

	        mockMvc.perform(
	                delete("/answer/deleteanswerbyid/{id}", answer.getIdAnswer()))
	                .andExpect(status().isOk());

	        verify(ansService, times(1)).findOne(answer.getIdAnswer());
	        verify(ansService, times(1)).deleteAnswer(answer.getIdAnswer());
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test delete answer fail 404 not found
	     */
	    
	    @Test
	    public void test_delete_answer_fail_404_not_found() throws Exception {
	    	Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);

	        when(ansService.findOne(answer.getIdAnswer())).thenReturn(null);

	        mockMvc.perform(
	                delete("/answer/deleteanswerbyid/{id}", answer.getIdAnswer()))
	                .andExpect(status().isNotFound());

	        verify(ansService, times(1)).findOne(answer.getIdAnswer());
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test update answer
	     */
	    
	    @Test
	    public void test_update_answer_success() throws Exception {
	    	Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);

	    	when(ansService.findOne(answer.getIdAnswer())).thenReturn(answer);
	        doNothing().when(ansService).editAnswer(answer);

	        mockMvc.perform(
	                put("/answer/updateanswer/{id}", answer.getIdAnswer())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(answer)))
	                .andExpect(status().isOk());

	        verify(ansService, times(1)).findOne(answer.getIdAnswer());
	        verify(ansService, times(1)).editAnswer(answer);
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    /**
	     * Test update answer fail 404 not found
	     */
	    
	    @Test
	    public void test_update_answer_fail_404_not_found() throws Exception {
	    	Answer answer = new Answer(2, questionAnswer, 1, "The answer is thasdais", true);

	        when(ansService.findOne(answer.getIdAnswer())).thenReturn(null);

	        mockMvc.perform(
	                put("/answer/updateanswer/{id}", answer.getIdAnswer())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(answer)))
	                .andExpect(status().isNotFound());

	        verify(ansService, times(1)).findOne(answer.getIdAnswer());
	        verifyNoMoreInteractions(ansService);
	    }
	    
	    public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
}
