package pji.cbt.form;

import java.util.ArrayList;
import java.util.List;

import pji.cbt.entities.User;

public class FormAssignment {
	
	private String categories;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public List<Integer> explodeString(String categories){
		String split[] = categories.split(",");
		List<Integer> idCategory = new ArrayList<Integer>();
			for(int i=0; i<split.length; i++ ){
				idCategory.add(Integer.valueOf(split[i]));
			}
		return idCategory;
	}
	
}
