 package pji.cbt.form;

import java.util.List;

import pji.cbt.entities.Category;

public class FormAnswer {
	
	private int testId;
	
	private List<String> choices;

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choice) {
		this.choices = choice;
	}
	
}
