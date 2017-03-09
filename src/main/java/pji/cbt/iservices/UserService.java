package pji.cbt.iservices;


import java.util.List;
import pji.cbt.entities.User;


public interface UserService {
	
	public abstract List<User> findAllUser(int role_id);
	
	public abstract User findOne(long id);

	public abstract void createUser(User paramTester);
	
	public abstract void deleteOne(long id);
	
	public abstract User findOneUser(String username);
}
