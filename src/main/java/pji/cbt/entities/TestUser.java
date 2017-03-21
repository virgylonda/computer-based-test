package pji.cbt.entities;

import java.io.Serializable;
import java.util.Date;

public class TestUser implements Serializable{

	private int testId;
	private Date started;
	private Date ended;
	private double score;
	private User users;
	private Category categories;
	
	public TestUser() {
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnded() {
		return ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Category getCategories() {
		return categories;
	}

	public void setCategories(Category categories) {
		this.categories = categories;
	}	
}
