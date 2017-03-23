package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.TestUser;

@Mapper
public interface TestUserMapper {
	
	public List<TestUser> findTestByUserId(int userId);
	
	public List<TestUser> findTestHaveAssign(int userId);
	
	public void saveTest(TestUser testUser);
	
	public List<TestUser> findUserSummaryScore();
	
	public List<TestUser> findTestAssignment(int userId);
	
	public void deleteByIdUserAndIdCategory(TestUser testUser);
	
	public void updateStartTest(TestUser testUser);
	
	public void updateEndTest(TestUser testUser);
}
