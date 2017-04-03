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
		
		//Assert
		assertEquals(user.getUsername(),userEx.getUsername());
		assertEquals(user.getUserName(),userEx.getUserName());
		assertEquals(user.getEmail(),userEx.getEmail());
	}

}
