package pji.cbt.services;


import java.util.List;

import pji.cbt.entities.Organization;
import pji.cbt.entities.User;


public interface OrganizationService {
	
	public abstract List<User> findAllOrganization();
	
	public abstract Organization findOne(long id);

	public abstract void createOrganization(Organization organization);
		
	public abstract void deleteOne(long id);
	
	public void updateOrganization(Organization organization);
	
}
