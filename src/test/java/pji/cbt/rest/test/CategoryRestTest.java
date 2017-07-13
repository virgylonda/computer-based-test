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
	     * Test CreateCategory Question
	     * 
	    */
	    
	    
	    /**
	     * Test UpdateCategory Question by id
	     * 
	    */
	    
	    /**
	     * Test DeleteCategory Question by id
	     * 
	    */
	    
	    
	    
	    
	    /**
		 * Test GetAll Category Question
		 * 
		 * 
		 */
	    @Test
	    public void test_get_all_category() throws Exception {
	    	List<Category> category = Arrays.asList(
	    			new Category("Category1asda", "ini kategori 1asdas"),
	    			new Category("Category1asdaasd", "ini katasdegori 1asdas"));

	        when(catService.findAllCategory()).thenReturn(category);

	        mockMvc.perform(get("/category/getallcategory"))
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
	    
	    /**
		  * Test GetCategory by id
		  * 
		  * 
		  */
	    
	    @Test
	    public void test_get_category_by_id() throws Exception {
	        Category category = new Category("Category1asda", "ini kategori 1asdas");
	        when(catService.findOneCategory(1)).thenReturn(category);

	        mockMvc.perform(get("/category/getCategoryById/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.questionCategory", is("Category1asda")))
	                .andExpect(jsonPath("$.description", is("ini kategori 1asdas")));
	        		
	        verify(catService, times(1)).findOneCategory(1);
	        verifyNoMoreInteractions(catService);
	    }
	    
	    
	    /**
	     * Test CategoryDetail by id
	     * 
	     */
	    
	    
}
