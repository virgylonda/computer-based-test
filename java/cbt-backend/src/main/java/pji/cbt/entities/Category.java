package pji.cbt.entities;

import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4739327016119277121L;
	private int idCategory;
	private String questionCategory;
	private String description;
	private String questionType;
	private int timeLimit;
	
	private Organization organization;
	
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category(){
	}
	
	public Category(String questionCategory, String description){
		this.questionCategory = questionCategory;
		this.description = description;
	}
	
	public Category(int idCategory, String questionCategory, String description, String questionType, int timeLimit) {
		super();
		this.idCategory = idCategory;
		this.questionCategory = questionCategory;
		this.description = description;
		this.questionType = questionType;
		this.timeLimit = timeLimit;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}	
	
		
		
	
}
