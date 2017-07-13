package pji.cbt.rest.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
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
	 
	 /**
	  * Create Question by id Question and Category Question
	  * 
	  * 
	  */
	 
	 /**
	  * Update Question by id Question
	  * 
	  * 
	  */
	 
	 /**
	  * Delete Question by id Question 
	  */
	 
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
	    
	 
	 
}
