package pji.cbt.test;

import static org.junit.Assert.*;
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
	public void createNewUserReturnNewUser() {
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

	@Test
	public void deleteUserReturnNull() {
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
	public void updateUserReturnChangeUser() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrul1528735687236","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Muh","za@gmail.com",role);
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
	public void updateUserPasswordReturnChangeUserPassword() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrul9091","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","MuhZafrullah","zaMuh@gmail.com",role);
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
	
	@Test
	public void findOneUserReturnNumber1() {
		//Arrange		
		Roles role = userSvc.findRoleById(2);
		User user = new User("zafrul554","$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.","Muhdiyah","Bravo@gmail.com",role);
		
		//Act
		userSvc.createUser(user);
		System.out.println(user.getUserId());
		User userEx = userSvc.findOne(user.getUserId());
		System.out.println(user.getUsername());
		System.out.println(user.getUserName());
		System.out.println(user.getRoles());
		
		//Assert
		assertEquals(user.getUserName(),userEx.getUserName());
	}
}
