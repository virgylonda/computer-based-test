package pji.cbt.rest.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
	
	private Question questionAnswer;
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
	 
	 // =========================================== Create Qestion ========================================

	    @Test
	    public void createQuestion() throws Exception {
	        Question question = new Question(1, 2, "question 1", category);

	        when(quesService.exists(question)).thenReturn(false);
	        doNothing().when(quesService).createQuestion(question);

	        mockMvc.perform(
	                post("/question")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(question)))
	                        
	                .andExpect(status().isCreated())
	                .andExpect(header().string("location", containsString("/question/createquestion")));

	        verify(quesService, times(1)).exists(question);
	        verify(quesService, times(1)).createQuestion(question);
	        verifyNoMoreInteractions(quesService);
	        
	    }
	 
	    // =========================================== Update Existing Question ===================================

	    @Test
	    public void updateQuestion() throws Exception {
	        Question question = new Question(1, 2, "question 2", category);

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

	 
	 // =========================================== DeleteQuestion ============================================

	    @Test
	    public void deleteQuestion() throws Exception {
	        Question question = new Question(1, 2, "question 2", category);

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
	  * Test getAll question 		-
	  *      
	*/
	  @Test
	    public void test_get_all_question() throws Exception {
	    	List<Question> question = Arrays.asList(
	    			new Question(1, 2, "question 1", category));
	    			

	        when(quesService.findAllQuestion()).thenReturn(question);

	        mockMvc.perform(get("/question/getallquestion"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(1)))
	                .andExpect(jsonPath("$[0].idQuestion", is(1)))
	                .andExpect(jsonPath("$[0].orderingQuestion", is(2)))
	                .andExpect(jsonPath("$[0].question", is("question 1")))
	                .andExpect(jsonPath("$[0].category", is(category)));

	        verify(quesService, times(1)).findAllQuestion();
	        verifyNoMoreInteractions(quesService);
	    }
	  
	  /**
	     * Test getQuestion by Id
	     */
	    @Test
	    public void test_get_question_by_id() throws Exception {
	        Question question = new Question(1, 2, "question 1", category);
	        when(quesService.findOneQuestion(1)).thenReturn(question);

	        mockMvc.perform(get("/question/getallquestionbycategoryid/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.idQuestion", is(1)))
	                .andExpect(jsonPath("$.orderingQuestion", is(2)))
	                .andExpect(jsonPath("$.question", is("question 1")))
	                .andExpect(jsonPath("$.category", is(category)));
	        		
	        verify(quesService, times(1)).findOneQuestion(1);
	        verifyNoMoreInteractions(quesService);
	    }
	    // =========================================== Json String ===========================================

	    public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
}
