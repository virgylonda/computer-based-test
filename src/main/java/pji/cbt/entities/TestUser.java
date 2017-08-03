package pji.cbt.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TestUser implements Serializable{

	private int testId;
	private Date started;
	private Date ended;
	private double score;
	private String status;
	private User users;
	private List<Category> categories;
	
	public TestUser() {
	}

	public TestUser(int testId, Date started, Date ended, double score, String status, User users,
			List<Category> categories) {
		super();
		this.testId = testId;
		this.started = started;
		this.ended = ended;
		this.score = score;
		this.status = status;
		this.users = users;
		this.categories = categories;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}	
}
