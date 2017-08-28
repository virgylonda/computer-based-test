package pji.cbt.entities;

import java.io.Serializable;

/**
 * Answer Entity Class
 */
public class Answer implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498786525320489106L;

	private int idAnswer;
	private Question questionAnswer;
	private int orderingAnswer;
	private String answer;
	private boolean correctAnswer;
	
	private Organization organization;
	
	public Answer(){
		
	}
	
	public Answer(int idAnswer, Question questionAnswer, int orderingAnswer, String answer, boolean correctAnswer){
		this.idAnswer = idAnswer;
		this.questionAnswer = questionAnswer;
		this.orderingAnswer = orderingAnswer;
		this.answer = answer;
		this.correctAnswer = correctAnswer;
	}
	
	public Question getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(Question questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public int getIdAnswer() {
		return idAnswer;
	}
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}
	public int getOrderingAnswer() {
		return orderingAnswer;
	}
	public void setOrderingAnswer(int ordering) {
		this.orderingAnswer = ordering;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
