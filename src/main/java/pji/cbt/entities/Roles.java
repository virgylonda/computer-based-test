package pji.cbt.entities;

public class Roles {
	
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
