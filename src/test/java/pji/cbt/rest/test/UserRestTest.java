package pji.cbt.rest.test;


import java.util.Arrays;
import java.util.List;

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Category;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.rest.controller.UserRestController;
import pji.cbt.services.UserService;
import pji.cbt.util.CORSFilter;

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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class UserRestTest {
	
	
	private MockMvc mockMvc;
	private Roles role;
	
	 @Mock
	    private UserService usrService;
	
	 @InjectMocks
	    private UserRestController userController;
	
	 
	 @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(userController)
	                .addFilters(new CORSFilter())
	                .build();
	    }

	  
	    // =========================================== Create New User ========================================

	    @Test
	    public void create_user_success() throws Exception {
	        User user = new User("yesi","yesi123","Yesi Relita","yessirelita@gmail.com",role);

	        when(usrService.exists(user)).thenReturn(false);
	        doNothing().when(usrService).createUser(user);

	        mockMvc.perform(
	                post("/rest/user")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                        
	                .andExpect(status().isCreated())
	                .andExpect(header().string("location", containsString("/rest/user/createuser")));

	        verify(usrService, times(1)).exists(user);
	        verify(usrService, times(1)).createUser(user);
	        verifyNoMoreInteractions(usrService);
	        
	    }
	  

	    // =========================================== Update Existing User ===================================

	    @Test
	    public void updateuser() throws Exception {
	        User user = new User("yesi","yesi123","Yesi Relitaaaa","yessirelita@gmail.com",role);

	        when(usrService.findOne(user.getUserId())).thenReturn(user);
	        doNothing().when(usrService).updateUser(user);

	        mockMvc.perform(
	                put("/rest/user/updateuser/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isOk());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verify(usrService, times(1)).updateUser(user);
	        verifyNoMoreInteractions(usrService);
	    }

	 

	    // =========================================== Delete User ============================================

	    @Test
	    public void deleteuser() throws Exception {
	        User user = new User("yesi","yesi123","Yesi Relitaaaa","yessirelita@gmail.com",role);

	        when(usrService.findOne(user.getUserId())).thenReturn(user);
	        doNothing().when(usrService).deleteOne(user.getUserId());

	        mockMvc.perform(
	                delete("/rest/user/updateuser/{id}", user.getUserId()))
	                .andExpect(status().isOk());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verify(usrService, times(1)).deleteOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
	    }

	    // =========================================== Get All Actor =========================================

	    @Test
	    public void getAllActor() throws Exception {
	    	List<User> user = Arrays.asList(
	    			new User("yesi1","yesi123","Yesi Relita","yessirelita@gmail.com",role),
	    			new User("yesi2","yesi124","Yesi Relitaaa","yessirelita@gmail.com",role));

	        when(usrService.findAllUsers()).thenReturn(user);

	        mockMvc.perform(get("/rest/user/getallactor"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].username", is("yesi1")))
	                .andExpect(jsonPath("$[0].password", is("yesi123")))
	                .andExpect(jsonPath("$[0].name", is("Yesi Relita")))
	                .andExpect(jsonPath("$[0].email", is("yessirelita@gmail.com")))
	                .andExpect(jsonPath("$[0].role", is(role)))
	                .andExpect(jsonPath("$[1].username", is("yesi2")))
	                .andExpect(jsonPath("$[1].password", is("yesi124")))
	                .andExpect(jsonPath("$[1].name", is("Yesi Relitaa")))
	                .andExpect(jsonPath("$[1].email", is("yessirelita@gmail.com")))
	                .andExpect(jsonPath("$[1].role", is(role)));
	        verify(usrService, times(1)).findAllUsers();
	        verifyNoMoreInteractions(usrService);
	    }

	    // =========================================== Get All Users ==========================================

	    @Test
	    public void getallUsers() throws Exception {
	        List<User> users = Arrays.asList(
	                new User("yesi1","yesi123","Yesi Relita","yessirelita@gmail.com",role),
	                new User("yesi2","yesi123","Yesi Relitaa","yessirelita@gmail.com",role));

	        when(usrService.findAllUser(3)).thenReturn(users);

	        mockMvc.perform(get("/rest/user/getallUser"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].username", is("yesi1")))
	                .andExpect(jsonPath("$[0].password", is("yesi123")))
	                .andExpect(jsonPath("$[0].name", is("Yesi Relita")))
	                .andExpect(jsonPath("$[0].email", is("yessirelita@gmail.com")))
	                .andExpect(jsonPath("$[0].role", is(role)))
	                .andExpect(jsonPath("$[1].username", is("yesi2")))
	                .andExpect(jsonPath("$[1].password", is("yesi123")))
	                .andExpect(jsonPath("$[1].name", is("Yesi Relitaa")))
	                .andExpect(jsonPath("$[1].email", is("yessirelita@gmail.com")))
	                .andExpect(jsonPath("$[1].role", is(role)));
	        

	        verify(usrService, times(1)).findAllUser(3);
	        verifyNoMoreInteractions(usrService);
	    }
	    
	 // =========================================== Get User By ID =========================================

	    @Test
	    public void getUserby_id() throws Exception {
	        User user = new User("yesi1","yesi123","Yesi Relita","yessirelita@gmail.com",role);

	        when(usrService.findOne(24)).thenReturn(user);

	        mockMvc.perform(get("/rest/user/getuserbyid/{id}", 24))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.username", is("yesi")))
	        		.andExpect(jsonPath("$.password", is("yesi123")))
                	.andExpect(jsonPath("$.name", is("Yesi Relita")))
                	.andExpect(jsonPath("$.email", is("yessirelita@gmail.com")))
                	.andExpect(jsonPath("$.role", is(role)));

	        verify(usrService, times(1)).findOne(24);
	        verifyNoMoreInteractions(usrService);
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