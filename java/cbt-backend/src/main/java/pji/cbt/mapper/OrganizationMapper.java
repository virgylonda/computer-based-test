package pji.cbt.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Organization;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;

/**
 * 
 * This class act as Mapper 
 * Connect/mapping *Mapper.xml
 *  */
@Mapper
public interface OrganizationMapper {
	

	public abstract List<User> findAllOrganization();
	
	public abstract Organization findOne(long id);
	
	public abstract void deleteOne(long id);

	public abstract void createOrganization(Organization organization);
	
	public void updateOrganization(Organization organization);
	

}
