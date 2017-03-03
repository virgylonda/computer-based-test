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
	
	@Override
	public List<User> findAll() {
		return this.uMapper.findAll();
	}

}
