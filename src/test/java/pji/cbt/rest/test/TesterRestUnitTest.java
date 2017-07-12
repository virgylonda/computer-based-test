package pji.cbt.rest.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.util.CORSFilter;
import java.util.Date;
import pji.cbt.rest.controller.TesterRestController;
import pji.cbt.services.AnswerService;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class TesterRestUnitTest {
	
	private MockMvc mockMvc;
	
	private Category category;
	private Roles role;
	private Question questionAnswer;
	private Date started;
	private Date ended;
	private User users;

    @Mock
    private CategoryService catService;
    
    @Mock
    private AnswerService ansService;
    
    @Mock
    private QuestionService quesService;
    
    @Mock
    private UserService usrService;
    
    @Mock
    private TestUserService tstService;
    
    @InjectMocks
    private TesterRestController testerController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(testerController)
                .addFilters(new CORSFilter())
                .build();
    }
    
 // =========================================== Get All Category ==========================================

    @Test
    public void test_get_all_category() throws Exception {
    	List<Category> category = Arrays.asList(
    			new Category("Category1asda", "ini kategori 1asdas"),
    			new Category("Category1asdaasd", "ini katasdegori 1asdas"));

        when(catService.findAllCategory()).thenReturn(category);

        mockMvc.perform(get("/rest/tester/category/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].questionCategory", is("Category1asda")))
                .andExpect(jsonPath("$[0].description", is("ini kategori 1asdas")))
                .andExpect(jsonPath("$[1].questionCategory", is("Category1asdaasd")))
                .andExpect(jsonPath("$[1].description", is("ini katasdegori 1asdas")));

        verify(catService, times(1)).findAllCategory();
        verifyNoMoreInteractions(catService);
    }
    
 // =========================================== Get All Question ==========================================

    @Test
    public void test_get_all_question() throws Exception {
    	List<Question> question = Arrays.asList(
    			new Question(1, 2, "question 1", category));
    			

        when(quesService.findAllQuestion()).thenReturn(question);

        mockMvc.perform(get("/rest/tester/testers/allquestion"))
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
    
 // =========================================== Get All Answer ==========================================

    @Test
    public void test_get_all_answer() throws Exception {
    	List<Answer> answer = Arrays.asList(
    			new Answer(1, questionAnswer, 1, "The answer", true));
    			

        when(ansService.findAllAnswer()).thenReturn(answer);

        mockMvc.perform(get("/rest/tester/testers/answer"))
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
    
 // =========================================== View all user for assignment ==========================================

    @Test
    public void test_get_all_user_for_assignment() throws Exception {
    	List<User> user = Arrays.asList(
    			new User("username", "password", "Mas Edrik", "contoh@gmail.com", role));
    			

        when(usrService.findAllUser(3)).thenReturn(user);

        mockMvc.perform(get("/rest/tester/testers/assignment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("username")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].name", is("Mas Edrik")))
                .andExpect(jsonPath("$[0].email", is("contoh@gmail.com")))
                .andExpect(jsonPath("$[0].roles", is(role)));

        verify(usrService, times(1)).findAllUser(3);
        verifyNoMoreInteractions(usrService);
    }
    
 // =========================================== Get Category By ID =========================================

    @Test
    public void test_get_category_by_id() throws Exception {
        Category category = new Category("Category1asda", "ini kategori 1asdas");
        when(catService.findOneCategory(1)).thenReturn(category);

        mockMvc.perform(get("/rest/tester/testers/category/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.questionCategory", is("Category1asda")))
                .andExpect(jsonPath("$.description", is("ini kategori 1asdas")));
        		
        verify(catService, times(1)).findOneCategory(1);
        verifyNoMoreInteractions(catService);
    }
    
 // =========================================== Get Question By ID =========================================

    @Test
    public void test_get_question_by_id() throws Exception {
        Question question = new Question(1, 2, "question 1", category);
        when(quesService.findOneQuestion(1)).thenReturn(question);

        mockMvc.perform(get("/rest/tester/testers/question/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.idQuestion", is(1)))
                .andExpect(jsonPath("$.orderingQuestion", is(2)))
                .andExpect(jsonPath("$.question", is("question 1")))
                .andExpect(jsonPath("$.category", is(category)));
        		
        verify(quesService, times(1)).findOneQuestion(1);
        verifyNoMoreInteractions(quesService);
    }
    
    // =========================================== Get Answer By ID =========================================

    @Test
    public void test_get_answer_by_id() throws Exception {
        Answer answer = new Answer(1, questionAnswer, 1, "The answer is this", false);
        when(ansService.findOne(1)).thenReturn(answer);

        mockMvc.perform(get("/rest/tester/testers/answer/{id}", 1))
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
    

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
