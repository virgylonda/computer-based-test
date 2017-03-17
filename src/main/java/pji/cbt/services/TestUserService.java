package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.TestUser;

public interface TestUserService {
	
	public List<TestUser> findTestByUserId(Integer userId);
	
}
