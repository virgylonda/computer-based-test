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

	@Override
	public void saveTest(TestUser testUser) {
		uMapper.saveTest(testUser);
	}
	
}
