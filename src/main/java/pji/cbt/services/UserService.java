package pji.cbt.services;


import java.util.List;

import pji.cbt.entities.Roles;
import pji.cbt.entities.User;


public interface UserService {
	
	public abstract List<User> findAllUser(int role_id);

	public abstract List<User> findAllUsers();
	
	public abstract User findOne(long id);

	public abstract void createUser(User paramTester);
	
	public abstract Roles findRoleById(int id);
	
	public abstract void deleteOne(long id);
	
	public abstract User findOneUser(String username);
	
	public void updateUser(User paramUser);
	
	public void updatePassword(User paramUser);
	
	public List<Roles> findRoleAll();
	
}
