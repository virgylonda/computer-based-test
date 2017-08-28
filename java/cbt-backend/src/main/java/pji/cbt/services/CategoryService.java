package pji.cbt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pji.cbt.entities.Category;

@Service
public interface CategoryService {

	public abstract List<Category> findAllCategory();

	public abstract Category findOneCategory(long id);
	
	public abstract void deleteOne(long id);

	public abstract void createCategory(Category category);
	
	public void updateCategory(Category category);
	
	boolean exists(Category category);
}
