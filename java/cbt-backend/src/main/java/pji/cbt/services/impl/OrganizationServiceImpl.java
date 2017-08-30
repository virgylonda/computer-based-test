package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Organization;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.mapper.OrganizationMapper;
import pji.cbt.mapper.UserMapper;
import pji.cbt.services.OrganizationService;
import pji.cbt.services.UserService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationMapper oMapper;

	public OrganizationServiceImpl() {
	}

	@Override
	public List<User> findAllOrganization() {
		return oMapper.findAllOrganization();
	}

	@Override
	public Organization findOne(long id) {
		return oMapper.findOne(id);
	}

	@Override
	public void createOrganization(Organization organization) {
		oMapper.createOrganization(organization);
		
	}

	@Override
	public void updateOrganization(Organization organization) {
		oMapper.updateOrganization(organization);
		
	}

	@Override
	public void deleteOne(long id) {
		oMapper.deleteOne(id);
		
	}

}
