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
	
	private String userName;
	
	private String email;
	
	private Roles roles;

	public User() {
	}

	public User(String username, String password, String userName, String email, Roles roles) {
		this.username = username;
		this.password = password;
		this.userName = userName;
		this.email = email;
		this.roles = roles;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
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

	public String passwordToHash(String password){		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
}
