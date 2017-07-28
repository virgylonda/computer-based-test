package pji.cbt.rest.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Answer;
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
import static org.mockito.Matchers.refEq;
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

	 	/**
	     * Test getAllActor
	     */
	    @Test
	    public void getAllActor() throws Exception {
	    	List<User> user = Arrays.asList(
	    			new User("username","password","AllUser","allUser@gmail.com", role));

	        when(usrService.findAllUsers()).thenReturn(user);

	        mockMvc.perform(get("/rest/user/getallactor"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(1)))
	                .andExpect(jsonPath("$[0].username", is("username")))
	                .andExpect(jsonPath("$[0].password", is("password")))
	                .andExpect(jsonPath("$[0].name", is("AllUser")))
	                .andExpect(jsonPath("$[0].email", is("allUser@gmail.com")))
	                .andExpect(jsonPath("$[0].roles", is(role)));
	        
	        verify(usrService, times(1)).findAllUsers();
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test get All User [Role id = 3]
	     */
	    @Test
	    public void test_get_all_users() throws Exception {
	        
	        List<User> user = Arrays.asList(
	    			new User("username","password","AllUser","allUser@gmail.com", role));
	        
	        when(usrService.findAllUser(3)).thenReturn(user);

	        mockMvc.perform(get("/rest/user/getallUser", 1))
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
	     * Test getUser by ID
	     */
	    @Test
	    public void test_get_user_by_id() throws Exception {
	        User user = new User("username","password","AllUser","allUser@gmail.com", role);
	        
	        when(usrService.findOne(1)).thenReturn(user);

	        mockMvc.perform(get("/rest/user/getuserbyid/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.username", is("username")))
	                .andExpect(jsonPath("$.password", is("password")))
	                .andExpect(jsonPath("$.name", is("AllUser")))
	                .andExpect(jsonPath("$.email", is("allUser@gmail.com")))
	                .andExpect(jsonPath("$.roles", is(role)));
	        		
	        verify(usrService, times(1)).findOne(1);
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test getUser by ID fail 404 not found
	     */
	    
	    @Test
	    public void test_get_user_by_id_fail_404_not_found() throws Exception {
	        when(usrService.findOne(1)).thenReturn(null);

	        mockMvc.perform(get("/rest/user/getuserbyid/{id}", 1))
	                .andExpect(status().isNotFound());

	        verify(usrService, times(1)).findOne(1);
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test create new User
	     */
	    @Test
	    public void test_create_user_success() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.exists(user)).thenReturn(false);
	        doNothing().when(usrService).createUser(user);

	        mockMvc.perform(
	                post("/rest/user/createuser")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isCreated())
	                .andExpect(header().string("location", containsString("/rest/user/getuserbyid/0")));

	        verify(usrService, times(1)).exists(refEq(user));
	        verify(usrService, times(1)).createUser(refEq(user));
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test delete user
	     */
	    @Test
	    public void test_delete_user_success() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.findOne(user.getUserId())).thenReturn(user);
	        doNothing().when(usrService).deleteOne(user.getUserId());

	        mockMvc.perform(
	                delete("/rest/user/deleteuserbyid/{id}", user.getUserId()))
	                .andExpect(status().isOk());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verify(usrService, times(1)).deleteOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test delete user fail 404 not found
	     */
	    
	    @Test
	    public void test_delete_user_fail_404_not_found() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.findOne(user.getUserId())).thenReturn(null);

	        mockMvc.perform(
	                delete("/rest/user/deleteuserbyid/{id}", user.getUserId()))
	                .andExpect(status().isNotFound());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test update user
	     */
	    
	    @Test
	    public void test_update_user_success() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

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
	    
	    /**
	     * Test update user fail 404 not found
	     */
	    
	    @Test
	    public void test_update_user_fail_404_not_found() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.findOne(user.getUserId())).thenReturn(null);

	        mockMvc.perform(
	                put("/rest/user/updateuser/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isNotFound());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test update profile
	     */
	    
	    @Test
	    public void test_update_profile_success() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	    	when(usrService.findOne(user.getUserId())).thenReturn(user);
	        doNothing().when(usrService).updateUser(user);

	        mockMvc.perform(
	                put("/rest/user/updateprofile/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isOk());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verify(usrService, times(1)).updateUser(user);
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test update profile fail 404 not found
	     */
	    
	    @Test
	    public void test_update_profile_fail_404_not_found() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.findOne(user.getUserId())).thenReturn(null);

	        mockMvc.perform(
	                put("/rest/user/updateprofile/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isNotFound());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
	    }

	    /**
	     * Test update password
	     */
	    
	    @Test
	    public void test_update_password_success() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	    	when(usrService.findOne(user.getUserId())).thenReturn(user);
	        doNothing().when(usrService).updatePassword(user);;

	        mockMvc.perform(
	                put("/rest/user/updatepassword/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isOk());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verify(usrService, times(1)).updatePassword(user);
	        verifyNoMoreInteractions(usrService);
	    }
	    
	    /**
	     * Test update password fail 404 not found
	     */
	    
	    @Test
	    public void test_update_password_fail_404_not_found() throws Exception {
	    	User user = new User("username","password","AllUser","allUser@gmail.com", role);

	        when(usrService.findOne(user.getUserId())).thenReturn(null);

	        mockMvc.perform(
	                put("/rest/user/updatepassword/{id}", user.getUserId())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(user)))
	                .andExpect(status().isNotFound());

	        verify(usrService, times(1)).findOne(user.getUserId());
	        verifyNoMoreInteractions(usrService);
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