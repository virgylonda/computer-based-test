package pji.cbt.rest.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import pji.cbt.config.WebMvcConfig;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.rest.controller.TesterRestController;
import pji.cbt.rest.controller.UserRestController;
import pji.cbt.services.CategoryService;
import pji.cbt.services.UserService;
import pji.cbt.rest.test.CORSFilter;

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

public class TesterRestTestController {

	 private MockMvc mockMvc;

	    @Mock
	    private CategoryService catService;
	    
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

	    // =========================================== Get All Users ==========================================

	    @Test
	    public void test_get_all_success() throws Exception {
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
	    
//	    
//	    @Test
//	    public void test_find_all_question() throws Exception {
//	    	Question first = new question()
//	    		.idQuestion(29L)
//	    		.orderingQuestion(1)
//	    		.question("Kelompok bakteri yang dikenal dengan ”nenek moyang bakteri” yaitu ….")
//	    		.category()
//	    	
//////	    	public List<Question> findAllQuestion(){
//////	    		return quesSvc.findAllQuestion();
//////	    	
////	    	
////	    	
//	    }
	    
	    
	    
	    

	    public static String asJsonString(final Object obj) {
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            return mapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	}


