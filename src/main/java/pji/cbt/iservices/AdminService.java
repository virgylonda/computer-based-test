package pji.cbt.iservices;

import java.util.List;
import pji.cbt.entities.Admin;
import pji.cbt.entities.User;

public abstract interface AdminService {
	

	public abstract List<User> findAllUser(String roles);
	
	public abstract User findOne(long id);

	public abstract void createUser(User paramTester);
	
	public abstract void deleteOne(long id);
	
	public abstract User findOneUser(String username);
}
