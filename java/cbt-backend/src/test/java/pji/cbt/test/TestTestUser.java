package pji.cbt.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pji.cbt.services.TestUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTestUser {

	@Autowired
	private TestUserService testSvc;
	
	@Test
	public void findTestByUserId_Return_testByUserId() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void findTestHaveAssign_Return_testHaveAssign() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void findUserTestById_Return_userTestByUserId() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void saveTest_Return_saveTestToDatabase() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void findUserSummaryScore_Return_userSummaryScore() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void findTestAssignment_Return_testAssignment() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void deleteByIdUserAndIdCategory_Return_null() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void updateStartTest_Return_startTestUpdate() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void deleteByIdUserAndStatus_Return_nullByIdUserAndStatus() {
		//Arrange
		
		//Act
		
		//Assert
	}
	
	@Test
	public void updateEndTest_Return_endTestUpdate() {
		//Arrange
		
		//Act
		
		//Assert
	}

}
