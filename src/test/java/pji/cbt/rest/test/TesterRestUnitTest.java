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

import static org.mockito.Matchers.refEq;
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
    
    /**
     * Test get All Tester [Role id = 2]
     */
    @Test
    public void test_get_all_tester() throws Exception {
        
        List<User> user = Arrays.asList(
    			new User("username","password","AllUser","allUser@gmail.com", role));
        
        when(usrService.findAllUser(2)).thenReturn(user);

        mockMvc.perform(get("/alltester/getalltester", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].username", is("username")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].name", is("AllUser")))
                .andExpect(jsonPath("$[0].email", is("allUser@gmail.com")))
                .andExpect(jsonPath("$[0].roles", is(role)));
        		
        verify(usrService, times(1)).findAllUser(2);
        verifyNoMoreInteractions(usrService);
    }
    
    /**
     * Test assign test to user
     */
//    @Test
//    public void test_create_answer_success() throws Exception {
//        TestUser testUser = new TestUser(1, started, ended, 30.4, "Not Yet", users, category);
//
//        when(tstService.testExists(testUser)).thenReturn(false);
//        doNothing().when(tstService).saveTest(testUser);;
//
//        mockMvc.perform(
//                post("/alltester/assignment/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(testUser)))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("location", containsString("/testers/scores/1")));
//
//        verify(tstService, times(1)).testExists(refEq(testUser));
//        verify(tstService, times(1)).saveTest(refEq(testUser));
//        verifyNoMoreInteractions(ansService);
//    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
