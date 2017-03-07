package pji.cbt.entities;

import java.io.Serializable;

/**
 * User Entity Class
 */
public class User implements Serializable {

private Long user_id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String roles;

	public User() {
	}
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long userId) {
		this.user_id = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
		
}
