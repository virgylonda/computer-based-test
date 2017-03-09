package pji.cbt.entities;

import java.io.Serializable;

public class Question implements Serializable {
	
	private int id;
	private int categoryId;
	private int ordering;
	private String question;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getCategoryId() {
		return categoryId;
	}
	
}
