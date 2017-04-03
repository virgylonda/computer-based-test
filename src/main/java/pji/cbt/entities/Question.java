package pji.cbt.entities;

import java.io.Serializable;

public class Question implements Serializable {
	
	private int idQuestion;
	private int orderingQuestion;
	private String question;
	private Category category;
	
	
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
	
}
