package pji.cbt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.User;
import pji.cbt.iservices.UserService;
import pji.cbt.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper uMapper;

	public UserServiceImpl() {
	}

	public void createUser(User tester) {
		uMapper.createUser(tester);
	}

	public List<User> findAllUser(int role_id) {
		return uMapper.findAllUser(role_id);
	}

	@Override
	public User findOne(long id) {
		return uMapper.findOne(id);
	}

	@Override
	public void deleteOne(long id) {
		uMapper.deleteOne(id);
	}

	@Override
	public User findOneUser(String username) {
		return uMapper.findOneUser(username);
	}
}
