package pji.cbt.entities;

import java.io.Serializable;

public class Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8933782251010758042L;
	private int idQuestion;
	private int orderingQuestion;
	private String question;
	private Category category;
	
	private Organization organization;
	
	public Question() {
	}
	
	public Question(int idQuestion, int orderingQuestion, String question, Category category) {
		this.idQuestion = idQuestion;
		this.orderingQuestion = orderingQuestion;
		this.question = question;
		this.category = category;
	}
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int id) {
		this.idQuestion = id;
	}
	public int getOrderingQuestion() {
		return orderingQuestion;
	}
	public void setOrderingQuestion(int ordering) {
		this.orderingQuestion = ordering;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getCategory() {
		return category;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
