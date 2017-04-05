package pji.cbt.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pji.cbt.entities.Category;
import pji.cbt.services.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCategory {

	@Autowired
	private CategoryService ctgSvc;
	
	@Test
	public void findAllCayegory_Return_AllCategory() {
		//Arrange				
		List<Category> category = new ArrayList<Category>();
		
		//Act
		category = ctgSvc.findAllCategory();
		
		//Assert
		for(Category categories : category){
			assertNotNull(categories.getIdCategory());
			System.out.println(categories.getIdCategory());
			System.out.println(categories.getQuestionCategory());
		}
	}
	
	@Test
	public void findOneCategory_Return_OneCategory(){
		//Arange
		Category category = new Category();
		
		//Act
		category = ctgSvc.findOneCategory(19);
		
		//Assert
		assertEquals(category.getIdCategory(), 19);
		System.out.println(category.getQuestionCategory());
	}
	
//	@Test
//	public void deleteOneCategory_Return_Null(){
//		//Arrange
//		Category category = new Category("Cate","Category baru");
//		
//		//Act
//		ctgSvc.createCategory(category);
//		System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
//		System.out.println(category.getIdCategory());
//		System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
//		Category categoryEx = ctgSvc.findOneCategory(category.getIdCategory());
//		System.out.println(category.getIdCategory());
//		
//		//Assert
//		assertEquals(category.getQuestionCategory(), categoryEx.getQuestionCategory());
//	}
	
//	@Test
//	public void createCategory_Return_createCategory(){
//		//Arrange
//		//Act
//		//Assert
//	}
//	
//	@Test
//	public void updateCategory_Return_updateCategory(){
//		//Arrange
//		//Act
//		//Assert
//	}

}
