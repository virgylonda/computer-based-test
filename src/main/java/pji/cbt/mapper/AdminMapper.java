package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.User;

@Mapper
public abstract interface AdminMapper {

	public abstract List<User> findAllUser(int role_id);

	public abstract User findOneUser(String username);
	
	public abstract User findOne(long id);
	
	public abstract void deleteOne(long id);

	public abstract void createUser(User paramTester);
	
	
}
