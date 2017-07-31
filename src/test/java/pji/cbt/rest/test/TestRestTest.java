package pji.cbt.rest.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import pji.cbt.entities.Roles;
import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.rest.controller.AnswerRestController;
import pji.cbt.rest.controller.TestRestController;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;
import pji.cbt.util.CORSFilter;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class TestRestTest {

	private MockMvc mockMvc;
	
	private Category category;
	private Question questionAnswer;
	private Date started;
	private Date ended;
	private User users;
	private Roles role;
	
	@Mock
	private UserService usrService;
	
	@Mock
	private AnswerService ansService;
	
	@Mock
	private QuestionService quesService;
	
	@Mock
	private TestUserService testService;

	@InjectMocks
	private TestRestController testController;

	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(testController)
                .addFilters(new CORSFilter())
                .build();
    }
    
	/**
     * Test get all user [Role id = 3] for assignment
     */
    @Test
    public void test_get_all_users_for_assignment() throws Exception {
        
        List<User> user = Arrays.asList(
    			new User("username","password","AllUser","allUser@gmail.com", role));
        
        when(usrService.findAllUser(3)).thenReturn(user);

        mockMvc.perform(get("/test/getallassignedtest", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].username", is("username")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].name", is("AllUser")))
                .andExpect(jsonPath("$[0].email", is("allUser@gmail.com")))
                .andExpect(jsonPath("$[0].roles", is(role)));
        		
        verify(usrService, times(1)).findAllUser(3);
        verifyNoMoreInteractions(usrService);
    }
    
    /**
     * Test get test have assigned by user id
     */
    @Test
    public void test_get_test_have_assigned_by_user_id() throws Exception {
        
        List<TestUser> testUser = Arrays.asList(
    			new TestUser(1, started, ended, 30.4, "Not Yet", users, category));
        
        when(testService.findTestAssignment(1)).thenReturn(testUser);

        mockMvc.perform(get("/test/gettesthaveassignbyuserid/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[0].started", is(started)))
                .andExpect(jsonPath("$[0].ended", is(ended)))
                .andExpect(jsonPath("$[0].score", is(30.4)))
                .andExpect(jsonPath("$[0].status", is("Not Yet")))
                .andExpect(jsonPath("$[0].users", is(users)))
                .andExpect(jsonPath("$[0].categories", is(category)));
        		
        verify(testService, times(1)).findTestAssignment(1);
        verifyNoMoreInteractions(testService);
    }
    
    /**
     * Test get user score by user id
     */
    @Test
    public void test_get_user_score_by_user_id() throws Exception {
        
        List<TestUser> testUser = Arrays.asList(
    			new TestUser(1, started, ended, 30.4, "Not Yet", users, category));
        
        when(testService.findTestByUserId(1)).thenReturn(testUser);

        mockMvc.perform(get("/test/getallscorebyuserid/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[0].started", is(started)))
                .andExpect(jsonPath("$[0].ended", is(ended)))
                .andExpect(jsonPath("$[0].score", is(30.4)))
                .andExpect(jsonPath("$[0].status", is("Not Yet")))
                .andExpect(jsonPath("$[0].users", is(users)))
                .andExpect(jsonPath("$[0].categories", is(category)));
        		
        verify(testService, times(1)).findTestByUserId(1);
        verifyNoMoreInteractions(testService);
    }
    
    /**
     * Test get test have assign by user id
     */
    @Test
    public void test_get_test_have_assign_by_user_id() throws Exception {
        
        List<TestUser> testUser = Arrays.asList(
    			new TestUser(1, started, ended, 30.4, "Not Yet", users, category));
        
        when(testService.findTestHaveAssign(1)).thenReturn(testUser);

        mockMvc.perform(get("/test/list/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[0].started", is(started)))
                .andExpect(jsonPath("$[0].ended", is(ended)))
                .andExpect(jsonPath("$[0].score", is(30.4)))
                .andExpect(jsonPath("$[0].status", is("Not Yet")))
                .andExpect(jsonPath("$[0].users", is(users)))
                .andExpect(jsonPath("$[0].categories", is(category)));
        		
        verify(testService, times(1)).findTestHaveAssign(1);
        verifyNoMoreInteractions(testService);
    }
    
    /**
     * Test do test (list of question)
     */
    @Test
    public void test_do_test() throws Exception {
        
        List<Question> question = Arrays.asList(
    			new Question(1, 1, "The question", category));
        
        when(quesService.findQuestionRandomOrder(1)).thenReturn(question);

        mockMvc.perform(get("/test/dotest/{idcategory}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].idQuestion", is(1)))
                .andExpect(jsonPath("$[0].orderingQuestion", is(1)))
                .andExpect(jsonPath("$[0].question", is("The question")))
                .andExpect(jsonPath("$[0].category", is(category)));
        		
        verify(quesService, times(1)).findQuestionRandomOrder(1);
        verifyNoMoreInteractions(quesService);
    }
    
    /**
     * Test get answer by id question
     */
    @Test
    public void test_get_answer_by_id_question() throws Exception {
        
        List<Answer> answer = Arrays.asList(
    			new Answer(1, questionAnswer, 1, "The answer", true));
        
        when(ansService.findAnswerByQuestion(1)).thenReturn(answer);

        mockMvc.perform(get("/test/getanswer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].idAnswer", is(1)))
                .andExpect(jsonPath("$[0].questionAnswer", is(questionAnswer)))
                .andExpect(jsonPath("$[0].orderingAnswer", is(1)))
                .andExpect(jsonPath("$[0].answer", is("The answer")))
                .andExpect(jsonPath("$[0].correctAnswer", is(true)));
        		
        verify(ansService, times(1)).findAnswerByQuestion(1);
        verifyNoMoreInteractions(ansService);
    }
	
    /**
     * Test begin test
     */
    
    @Test
    public void test_begin_test_success() throws Exception {
    	TestUser testUser = new TestUser(1, started, ended, 30.4, "Not Yet", users, category);

    	when(testService.findUserTestById(testUser.getTestId())).thenReturn(testUser);
    	
        doNothing().when(testService).updateStartTest(testUser);

        mockMvc.perform(
                put("/test/begintest/{id}", testUser.getTestId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser)))
                .andExpect(status().isOk());

        verify(testService, times(1)).findUserTestById(testUser.getTestId());
        verify(testService, times(1)).updateStartTest(testUser);
        verifyNoMoreInteractions(testService);
    }
    
    /**
     * Test submit test
     */
    
    @Test
    public void test_submit_test_success() throws Exception {
    	TestUser testUser = new TestUser(1, started, ended, 30.4, "Not Yet", users, category);

    	when(testService.findUserTestById(testUser.getTestId())).thenReturn(testUser);
    	
        doNothing().when(testService).updateEndTest(testUser);

        mockMvc.perform(
                put("/test/submittest/{id}", testUser.getTestId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser)))
                .andExpect(status().isOk());

        verify(testService, times(1)).findUserTestById(testUser.getTestId());
        verify(testService, times(1)).updateEndTest(testUser);
        verifyNoMoreInteractions(testService);
    }
    
    /**
     * Test view all user for showing score
     */
 
    @Test
    public void test_view_all_user_score() throws Exception {
    	List<TestUser> testUser = Arrays.asList(
    			new TestUser(1, started, ended, 30.4, "Not Yet", users, category));
    	
        when(testService.findUserSummaryScore()).thenReturn(testUser);

        mockMvc.perform(get("/test/getalluserscore"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[0].started", is(started)))
                .andExpect(jsonPath("$[0].ended", is(ended)))
                .andExpect(jsonPath("$[0].score", is(30.4)))
                .andExpect(jsonPath("$[0].status", is("Not Yet")))
                .andExpect(jsonPath("$[0].users", is(users)))
                .andExpect(jsonPath("$[0].categories", is(category)));

        verify(testService, times(1)).findUserSummaryScore();
        verifyNoMoreInteractions(testService);
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
