package pji.cbt.rest.test;

import static org.hamcrest.Matchers.containsString;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.User;
import pji.cbt.rest.controller.QuestionRestController;
import pji.cbt.services.QuestionService;
import pji.cbt.util.CORSFilter;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class QuestionRestTest {

	private MockMvc mockMvc;
	
	private Category category;
	
	 @Mock
	    private QuestionService quesService;
	 
	 @InjectMocks
	    private QuestionRestController questionController;
	 
	 @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(questionController)
	                .addFilters(new CORSFilter())
	                .build();
	    }
	 
	 	 /**
	     * Test GetAllQuestion
	     */
	 
	    @Test
	    public void test_get_all_question() throws Exception {
	    	List<Question> question = Arrays.asList(
	    			new Question(1, 1, "The question", category));
	    			

	        when(quesService.findAllQuestion()).thenReturn(question);

	        mockMvc.perform(get("/question/getallquestion"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(1)))
	                .andExpect(jsonPath("$[0].idQuestion", is(1)))
	                .andExpect(jsonPath("$[0].orderingQuestion", is(1)))
	                .andExpect(jsonPath("$[0].question", is("The question")))
	                .andExpect(jsonPath("$[0].category", is(category)));

	        verify(quesService, times(1)).findAllQuestion();
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test getQuestion by ID
	     */
	    @Test
	    public void test_get_question_by_id() throws Exception {
	        Question question = new Question(1, 1, "The question", category);
	        
	        when(quesService.findOneQuestion(1)).thenReturn(question);

	        mockMvc.perform(get("/question/getdetailquestion/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.idQuestion", is(1)))
	                .andExpect(jsonPath("$.orderingQuestion", is(1)))
	                .andExpect(jsonPath("$.question", is("The question")))
	                .andExpect(jsonPath("$.category", is(category)));
	        		
	        verify(quesService, times(1)).findOneQuestion(1);
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test getQuestion by ID fail 404 not found
	     */
	    @Test
	    public void test_get_question_by_id_fail_404_not_found() throws Exception {
	        when(quesService.findOneQuestion(1)).thenReturn(null);

	        mockMvc.perform(get("/question/getdetailquestion/{id}", 1))
	                .andExpect(status().isNotFound());

	        verify(quesService, times(1)).findOneQuestion(1);
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test create new question
	     */
	    @Test
	    public void test_create_question_success() throws Exception {
	    	Question question = new Question(1, 1, "The question", category);

	        when(quesService.exists(question)).thenReturn(false);
	        doNothing().when(quesService).createQuestion(question);;

	        mockMvc.perform(
	                post("/question/createquestion")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(question)))
	                .andExpect(status().isCreated())
	                .andExpect(header().string("location", containsString("/question/getdetailquestion/1")));

	        verify(quesService, times(1)).exists(refEq(question));
	        verify(quesService, times(1)).createQuestion(refEq(question));
	        verifyNoMoreInteractions(quesService);
	    }

	    /**
	     * Test delete question
	     */
	    @Test
	    public void test_delete_question_success() throws Exception {
	    	Question question = new Question(1, 1, "The question", category);

	        when(quesService.findOneQuestion(question.getIdQuestion())).thenReturn(question);
	        doNothing().when(quesService).deleteQuestion(question.getIdQuestion());

	        mockMvc.perform(
	                delete("/question/deletequestion/{id}", question.getIdQuestion()))
	                .andExpect(status().isOk());

	        verify(quesService, times(1)).findOneQuestion(question.getIdQuestion());
	        verify(quesService, times(1)).deleteQuestion(question.getIdQuestion());
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test delete question fail 404 not found
	     */
	    
	    @Test
	    public void test_delete_question_fail_404_not_found() throws Exception {
	    	Question question = new Question(1, 1, "The question", category);

	        when(quesService.findOneQuestion(question.getIdQuestion())).thenReturn(null);

	        mockMvc.perform(
	                delete("/question/deletequestion/{id}", question.getIdQuestion()))
	                .andExpect(status().isNotFound());

	        verify(quesService, times(1)).findOneQuestion(question.getIdQuestion());
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test update question
	     */
	    
	    @Test
	    public void test_update_question_success() throws Exception {
	    	Question question = new Question(1, 1, "The question", category);

	    	when(quesService.findOneQuestion(question.getIdQuestion())).thenReturn(question);
	        doNothing().when(quesService).editQuestion(question);

	        mockMvc.perform(
	                put("/question/updatequestion/{id}", question.getIdQuestion())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(question)))
	                .andExpect(status().isOk());

	        verify(quesService, times(1)).findOneQuestion(question.getIdQuestion());
	        verify(quesService, times(1)).editQuestion(question);
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * Test update question fail 404 not found
	     */
	    
	    @Test
	    public void test_update_question_fail_404_not_found() throws Exception {
	    	Question question = new Question(1, 1, "The question", category);

	        when(quesService.findOneQuestion(question.getIdQuestion())).thenReturn(null);

	        mockMvc.perform(
	                put("/question/updatequestion/{id}", question.getIdQuestion())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(question)))
	                .andExpect(status().isNotFound());

	        verify(quesService, times(1)).findOneQuestion(question.getIdQuestion());
	        verifyNoMoreInteractions(quesService);
	    }
	    
	    /**
	     * JSON String
	     */

	    public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
}
