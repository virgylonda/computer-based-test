package pji.cbt.form;

public class FormAnswer {
	
	private int questionId;
	
	private int testId;
	
	private int choices;
	
	private String checkBut;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getChoices() {
		return choices;
	}

	public void setChoices(int choice) {
		this.choices = choice;
	}

	public String getCheckBut() {
		return checkBut;
	}

	public void setCheckBut(String checkBut) {
		this.checkBut = checkBut;
	}
	
}
