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
import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.rest.controller.CategoryRestController;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;
import pji.cbt.util.CORSFilter;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
public class CategoryRestTest {

private MockMvc mockMvc;
	
	private Category category;
	
	 @Mock
	    private CategoryService catService;
	@Mock
    private QuestionService quesService;
	
	
	  @InjectMocks
	    private CategoryRestController categoryController;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(categoryController)
	                .addFilters(new CORSFilter())
	                .build();
	    }
	    
	    /**
	     * Test GetAllCategory
	     */
	 
	    @Test
	    public void test_get_all_category() throws Exception {
	    	List<Category> category = Arrays.asList(new Category(1, "Ujian Matematika", "Test Matematika Dasar untuk siswa SMP", "Multiple Choice", 60));
	    			

	        when(catService.findAllCategory()).thenReturn(category);

	        mockMvc.perform(get("/category/getallcategory"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].idCategory", is(1)))
            .andExpect(jsonPath("$[0].questionCategory", is("Ujian Matematika")))
            .andExpect(jsonPath("$[0].description", is("Test Matematika Dasar untuk siswa SMP")))
            .andExpect(jsonPath("$[0].questionType", is("Multiple Choice")))
            .andExpect(jsonPath("$[0].timeLimit", is(60)));

		    verify(catService, times(1)).findAllCategory();
		    verifyNoMoreInteractions(catService);

	    }
	    
	    /**
		  * Test GetCategory by id
		  */
	    
	    @Test
	    public void test_get_category_by_id() throws Exception {
	    	Category category = new Category(1, "Ujian Matematika", "Test Matematika Dasar untuk siswa SMP", "Multiple Choice", 60);

	        when(catService.findOneCategory(1)).thenReturn(category);

	        mockMvc.perform(get("/category/getCategoryById/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.idCategory", is(1)))
	                .andExpect(jsonPath("$.questionCategory", is("Ujian Matematika")))
	                .andExpect(jsonPath("$.description", is("Test Matematika Dasar untuk siswa SMP")))
	                .andExpect(jsonPath("$.questionType", is("Multiple Choice")))
	                .andExpect(jsonPath("$.timeLimit", is(60)));
	        		
	        verify(catService, times(1)).findOneCategory(1);
	        verifyNoMoreInteractions(catService);
	    }
	    
	    /**
	     * Test getCategory by ID fail 404 not found
	     */
	    
	    @Test
	    public void test_get_category_by_id_fail_404_not_found() throws Exception {
	        when(catService.findOneCategory(1)).thenReturn(null);

	        mockMvc.perform(get("/category/getCategoryById/{id}", 1))
	                .andExpect(status().isNotFound());

	        verify(catService, times(1)).findOneCategory(1);
	        verifyNoMoreInteractions(catService);
	    }
	    
	    /**
	     * Test delete category
	     */
	    @Test
	    public void test_delete_category_success() throws Exception {
	    	Category category = new Category(1, "Ujian Matematika", "Test Matematika Dasar untuk siswa SMP", "Multiple Choice", 60);

	        when(catService.findOneCategory(category.getIdCategory())).thenReturn(category);
	        doNothing().when(catService).deleteOne(category.getIdCategory());

	        mockMvc.perform(
	                delete("/category/deletecategory/{id}", category.getIdCategory()))
	                .andExpect(status().isOk());

	        verify(catService, times(1)).findOneCategory(category.getIdCategory());
	        verify(catService, times(1)).deleteOne(category.getIdCategory());
	        verifyNoMoreInteractions(catService);
	    }
	    

	    /**
	     * Test delete category fail 404 not found
	     */
	    
	    @Test
	    public void test_delete_category_fail_404_not_found() throws Exception {
	    	Category category = new Category(1, "Ujian Matematika", "Test Matematika Dasar untuk siswa SMP", "Multiple Choice", 60);

	        when(catService.findOneCategory(category.getIdCategory())).thenReturn(null);

	        mockMvc.perform(
	                delete("/category/deletecategory/{id}", category.getIdCategory()))
	                .andExpect(status().isNotFound());

	        verify(catService, times(1)).findOneCategory(category.getIdCategory());
	        verifyNoMoreInteractions(catService);
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
