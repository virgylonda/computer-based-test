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

import com.fasterxml.jackson.databind.ObjectMapper;

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

	        mockMvc.perform(get("/answer/getanswerbyid/{id}", 1))
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
	     * Test DeleteAnswer by ID
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
