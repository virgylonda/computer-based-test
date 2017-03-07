package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pji.cbt.entities.Admin;
import pji.cbt.entities.User;

@Mapper
public abstract interface AdminMapper {

	@Select({ "SELECT * FROM tb_user WHERE roles = '${roles}'" })
	public abstract List<User> findAllUser(@Param("roles") String roles);
	
	@Select({ "SELECT * FROM tb_user WHERE username = '${username}'" })
	public abstract User findOneUser(@Param("username") String username);
	
	@Select({ "SELECT * FROM tb_user WHERE user_id= ${id}" })
	public abstract User findOne(@Param("id") long id);
	
	@Delete({ "DELETE FROM tb_user WHERE user_id = ${id}" })
	public abstract void deleteOne(@Param("id") long id);

	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
	@Insert({ "INSERT INTO tb_user(username,password,name,roles) VALUES(#{username}, #{password}, #{name}, #{roles})"})
	public abstract void createUser(User paramTester);
	
	
}
