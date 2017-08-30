package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.mapper.UserMapper;
import pji.cbt.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper uMapper;

	public UserServiceImpl() {
	}

	public void createUser(User tester) {
		uMapper.createUser(tester);
	}
	
	public Roles findRoleById (int id) {
		return uMapper.findRoleById(id);
	}
	
	public List<User> findAllUser(int role_id) {
		return uMapper.findAllUser(role_id);
	}

	public List<User> findAllUsers() {
		return uMapper.findAllUsers();
	}
	
	public User findOne(long id) {
		return uMapper.findOne(id);
	}

	public void deleteOne(long id) {
		uMapper.deleteOne(id);
	}

	public User findOneUser(String username) {
		return uMapper.findOneUser(username);
	}

	public void updateUser(User paramUser) {
		uMapper.updateUser(paramUser);
	}

	public void updatePassword(User paramUser) {
		uMapper.updatePassword(paramUser);
	}
	
	public List<Roles> findRoleAll() {
		return uMapper.findRoleAll();
	}

	@Override
	public boolean exists(User user) {
		return findOne(user.getUserId()) != null;
	}

	@Override
	public boolean userExists(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findUserInOrg(int role_id, long orgId) {
 		return uMapper.findUserInOrg(role_id, orgId);
	}

}
