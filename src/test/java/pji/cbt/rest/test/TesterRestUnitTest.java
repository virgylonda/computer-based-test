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
    
    
 
    
    

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
