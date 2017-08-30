package pji.cbt.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pji.cbt.entities.Roles;
import pji.cbt.entities.User;

/**
 * 
 * This class act as Mapper 
 * Connect/mapping *Mapper.xml
 *  */
@Mapper
public interface UserMapper {
	
	public abstract List<User> findAllUser(int role_id);

	public abstract List<User> findAllUsers();
	
	public abstract User findOneUser(String username);
	
	public abstract User findOne(long id);
	
	public abstract Roles findRoleById(int id);
	
	public abstract void deleteOne(long id);

	public abstract void createUser(User paramTester);
	
	public void updateUser(User paramUser);
	
	public void updatePassword(User paramUser);
	
	public List<Roles> findRoleAll();

	public abstract List<User> findUserInOrg(@Param("roleId") int roleId, @Param("orgId") long orgId);
}
