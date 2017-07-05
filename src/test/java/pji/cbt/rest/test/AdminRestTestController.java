package pji.cbt.rest.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.rest.controller.AdminRestController;
import pji.cbt.services.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest

public class AdminRestTestController {
private static Logger logger = Logger.getLogger(AdminRestController.class);
	
	@Autowired
	private UserService userSvc;
	
	/**
	 *  View List of tester account
	 */
	@Test
	public void findAllTester_Return_AllUser() {
		//Arrange				
		List<User> users = new ArrayList<User>();
		
		//Act
		users = userSvc.findAllUser(2);
		
		//Assert
		for(User user : users){
			assertEquals(user.getRoles().getRoleId(),2);
			logger.info(user.getUsername());
		}
	}
	
		
	/**
	 * View List of User account
	 */
	@Test 
	public void findAllUser_Return_AllUser() {
		//Arrange				
		List<User> users = new ArrayList<User>();
		
		//Act
		users = userSvc.findAllUser(3);
		
		//Assert
		for(User user : users){
			assertEquals(user.getRoles().getRoleId(),3);
			logger.info(user.getUsername());
		}
	}
	
	
	
	/**
	 * List of All Actor
	 */
	@Test
	public void findRoleAll_Return_RoleAll() {
		//Arrange				
		List<Roles> roles = new ArrayList<Roles>();
		int[] x = {2,3};
		int i = 0; 
		
		//Act
		roles = userSvc.findRoleAll();
		
		//Assert
		
		for(Roles role : roles){
			assertEquals(role.getRoleId(), x[i]);
			i++;
			 logger.info(role.getRoleId());
		}
	}
	
	/**
	 * Retrieve a All Actor by id
	 */
	@Test
	public void findOneUserById_Return_IdUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("kikimf9","$2a$10$HiGY1uv5pmNpDU4uUcS97uWwhHFAPhXJ5ZemggsgrAPKYsTFbgpLW.","Risky Miftahul Fajriii","kikihootowl111@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		logger.info(user.getUserId());
		User userEx = userSvc.findOne(user.getUserId());
		logger.info(user.getUserId());
		logger.info(user.getUsername());
		
		//Assert
		assertEquals(user.getUserId(),userEx.getUserId());
	}
	
	
	/**
	 *  Creaye New User
	 */
	@Test
	public void createNewUser() {
		//Arrange
		Roles role = userSvc.findRoleById(3);
		User user = new User("Yessy","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","ButarButar","yessirelita@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		User userEx = userSvc.findOne(user.getUserId());
		logger.info(user.getUserId());
		
		//Assert
		assertEquals(user.getUsername(),userEx.getUsername());
		assertEquals(user.getName(),userEx.getName());
		assertEquals(user.getEmail(),userEx.getEmail());
	}
	
	/**
	 * Update User
	 */
	@Test
	public void updateUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(3);
		User user = new User("Masri123","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Pak","masri@gmail.com",role);
		String userName = "Pakpahan";
		
		//Act
		userSvc.createUser(user);
		logger.info(user.getUserId());
		
		user.setName(userName);
		logger.info(user.getUserId());
		userSvc.updateUser(user);
		
		//Assert
		assertEquals(userName,user.getName());
	}
	
	
	/**
	 * Delete User
	 */
	@Test
	public void deleteUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(3);
		User user = new User("yesi123","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Butarbutar","yessi@gmail.com",role);
	
		//Act
		userSvc.createUser(user);
		logger.info(user.getUserId());
		userSvc.deleteOne(user.getUserId());
		User userDel = userSvc.findOne(user.getUserId());
		
		//Assert
		assertNull(userDel);
	}
	
	
	/**
	 * Change Password User
	 */
	@Test
	public void updateUserPassword() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("copy","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Risky Miftahull","riski.miftahul@gmail.com",role);
		String password = "$10$HiGY1uv5pmNpDU4uUcS97uWwhHFAPhXJ5ZemggsgrAPKYsTFbgpLW";
		
		//Act
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		
		user.setPassword(password);
		System.out.println(user.getUserId());
		System.out.println(user.getPassword());
		userSvc.updatePassword(user);
		
		//Assert
		assertEquals(password, user.getPassword());
	}

	
	

}
