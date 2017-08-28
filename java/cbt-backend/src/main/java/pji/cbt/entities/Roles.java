package pji.cbt.entities;

import java.io.Serializable;

public class Roles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 652948249212186561L;

	private int roleId;
	
	private String roleName;
	
	private String description;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int id) {
		this.roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String name) {
		this.roleName = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return "Roles [id=" + roleId + ", name=" + roleName + ", description=" + description + "]";
	}

}
