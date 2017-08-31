package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.TestUser;
import pji.cbt.mapper.TestUserMapper;
import pji.cbt.services.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService {

	@Autowired
	private TestUserMapper uMapper;

	public TestUserServiceImpl() {
	}

	public List<TestUser> findTestByUserId(int userId) {
		return uMapper.findTestByUserId(userId);
	}

	public List<TestUser> findUserSummaryScore(long orgId) {
		return uMapper.findUserSummaryScore(orgId);
	}

	@Override
	public void saveTest(TestUser testUser) {
		uMapper.saveTest(testUser);
	}

	@Override
	public List<TestUser> findTestAssignment(int userId, long orgId) {
		return uMapper.findTestAssignment(userId, orgId);
	}

	@Override
	public void deleteByIdUserAndIdCategory(TestUser testUser) {
		uMapper.deleteByIdUserAndIdCategory(testUser);
	}

	@Override
	public void updateStartTest(TestUser testUser) {
		uMapper.updateStartTest(testUser);
	}

	@Override
	public List<TestUser> findTestHaveAssign(int userId) {
		return uMapper.findTestHaveAssign(userId);
	}

	@Override
	public void updateEndTest(TestUser testUser) {
		uMapper.updateEndTest(testUser);
	}

	@Override
	public void deleteByIdUserAndStatus(int userId) {
		uMapper.deleteByIdUserAndStatus(userId);
	}

	@Override
	public TestUser findUserTestById(int testId) {
		return uMapper.findUserTestById(testId);
	}

	@Override
	public boolean testExists(TestUser testUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TestUser> findTestAssignment(int userId) {
		return findTestAssignment( userId,0l);
	}

}
