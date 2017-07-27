package pji.cbt.entities;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * User Entity Class
 */
public class User implements Serializable {

	private int userId;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private int role_id;

	private Roles roles;

	public User() {
	}

	public User(String username, String password, String name, String email, Roles roles) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public User(int userId, String username, String password, String name, String email, int role_id) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.role_id = role_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String passwordToHash(String password){		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
}
