package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.TestUser;

@Mapper
public interface TestUserMapper {
	
	public List<TestUser> findTestByUserId(int userId);
	
	public void saveTest(TestUser testUser);
}
