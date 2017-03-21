package pji.cbt.form;

import java.util.Date;
import java.util.List;

import pji.cbt.entities.Category;

public class FormAnswer {

	private Category category;
	
	private String startTest;
	
	private List<String> choices;

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choice) {
		this.choices = choice;
	}
	
	public String getStartTest() {
		return startTest;
	}

	public void setStartTest(String startTest) {
		this.startTest = startTest;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
