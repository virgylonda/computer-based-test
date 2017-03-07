package pji.cbt.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pji.cbt.entities.Admin;
import pji.cbt.entities.User;
import pji.cbt.iservices.AdminService;
import pji.cbt.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper aMapper;

	public AdminServiceImpl() {
	}

	public List<Admin> findAllAdmin() {
		return aMapper.findAllAdmin();
	}

	public void createUser(User tester) {
		aMapper.createUser(tester);
	}

	public List<User> findAllUser(String roles) {
		return aMapper.findAllUser(roles);
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
