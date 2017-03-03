package pji.cbt.entities;

import java.io.Serializable;

/**
 * User Entity Class
 */
public class User implements Serializable {

	private String id;
	private String username;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
