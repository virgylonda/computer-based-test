package pji.cbt.entities;

import java.io.Serializable;

public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2876173359917473908L;

	private long id;
	private String orgType;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}
