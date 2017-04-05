package pji.cbt.entities;

import java.io.Serializable;

public class Category implements Serializable {

	private int idCategory;
	private String questionCategory;
	private String description;
	
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
}
