package pji.cbt.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pji.cbt.entities.User;
import pji.cbt.iservices.AdminService;
import pji.cbt.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper aMapper;

	public AdminServiceImpl() {
	}

	public void createUser(User tester) {
		aMapper.createUser(tester);
	}

	public List<User> findAllUser(int role_id) {
		return aMapper.findAllUser(role_id);
	}

	@Override
	public User findOne(long id) {
		return aMapper.findOne(id);
	}

	@Override
	public void deleteOne(long id) {
		aMapper.deleteOne(id);
	}

	@Override
	public User findOneUser(String username) {
		return aMapper.findOneUser(username);
	}

}
