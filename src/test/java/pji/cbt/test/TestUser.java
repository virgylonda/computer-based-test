package pji.cbt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

	@Autowired
	private UserService userSvc;
	
	@Test
	public void findAllUser_Return_AllUser() {
		//Arrange				
		List<User> users = new ArrayList<User>();
		
		//Act
		users = userSvc.findAllUser(2);
		
		//Assert
		for(User user : users){
			assertEquals(user.getRoles().getRoleId(),2);
			System.out.println(user.getUsername());
		}
	}
	
	@Test
	public void findOneUserById_Return_IdUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("firdaus","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Firadaus","firadaus@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		User userEx = userSvc.findOne(user.getUserId());
		System.out.println(user.getUserId());
		System.out.println(user.getUsername());
		
		//Assert
		assertEquals(user.getUserId(),userEx.getUserId());
	}
	
	@Test
	public void createNewUser_Return_NewUser() {
		//Arrange
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrul","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Muhammad Zafrullah","zafrul@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		User userEx = userSvc.findOne(user.getUserId());
		System.out.println(user.getUserId());
		
		//Assert
		assertEquals(user.getUsername(),userEx.getUsername());
		assertEquals(user.getUserName(),userEx.getUserName());
		assertEquals(user.getEmail(),userEx.getEmail());
	}

	
	//
	
	
	@Test
	public void deleteUser_Return_Null() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrul12","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Muhammad Zafrullah","zafrul@gmail.com",role);
	
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		userSvc.deleteOne(user.getUserId());
		User userDel = userSvc.findOne(user.getUserId());
		
		//Assert
		assertNull(userDel);
	}
	
	@Test
	public void findOneUserByUsername_Return_Username() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("ichsan","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Ichsan Pribadi","ichsanprivatee.IP@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		User userEx = userSvc.findOneUser(user.getUsername());
		System.out.println(user.getUsername());
		System.out.println(user.getUserName());
		System.out.println(user.getRoles());
		
		//Assert
		assertEquals(user.getUsername(),userEx.getUsername());
	}
	
	@Test
	public void updateUser_Return_ChangeUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrulloh","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Muh","za@gmail.com",role);
		String userName = "Muhammad Zafrullah";
		
		//Act
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		
		user.setUserName(userName);
		System.out.println(user.getUserId());
		userSvc.updateUser(user);
		
		//Assert
		assertEquals(userName,user.getUserName());
	}
	
	@Test
	public void updateUserPassword_Return_ChangeUserPassword() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("risky","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Risky Miftahull","riski.miftahul@gmail.com",role);
		String password = "$2a$06$pcFNCQflblU0GizKEsue0etBNqHOgYmDrbVns7adqLp7DfbNhfdJC";
		
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
	
	
	//	
	
}
