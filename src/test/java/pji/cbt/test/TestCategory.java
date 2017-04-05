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
	 }

	@Test
	public void deleteOneCategory_Return_Null() {
		// Arrange
		Category category = new Category("Mantap", "Mantap Banget");

		// Act
		ctgSvc.createCategory(category);
		ctgSvc.deleteOne(category.getIdCategory());
		Category categoryDel = ctgSvc.findOneCategory(category.getIdCategory());

		// Assert
		assertNull(categoryDel);
	}

	@Test
	public void createCategory_Return_createCategory() {
		// Arrange
		Category category = new Category("Cate", "Category baru");

		// Act
		ctgSvc.createCategory(category);
		Category categoryEx = ctgSvc.findOneCategory(category.getIdCategory());

		// Assert
		assertEquals(category.getQuestionCategory(), categoryEx.getQuestionCategory());
		assertEquals(category.getDescription(), categoryEx.getDescription());
	}

	 @Test
	 public void updateCategory_Return_updateCategory(){
		//Arrange
		Category category = new Category("Cate", "Category yang terbaru banget");
		String questionCategory = "Category Update";
		String description = "Category setelah diupdate";
		
		// Act
		ctgSvc.createCategory(category);
		category.setQuestionCategory(questionCategory);
		category.setDescription(description);
		ctgSvc.updateCategory(category);
		Category categoryUpdate = ctgSvc.findOneCategory(category.getIdCategory());
		
		//Assert
		assertEquals(category.getIdCategory(), categoryUpdate.getIdCategory());
		assertEquals(category.getQuestionCategory(), categoryUpdate.getQuestionCategory());
		assertEquals(category.getDescription(), categoryUpdate.getDescription());
	 }

}
