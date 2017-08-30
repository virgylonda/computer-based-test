package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Category;
import pji.cbt.mapper.CategoryMapper;
import pji.cbt.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper cMapper;
	
	@Override
	public List<Category> findAllCategory(long orgId) {
		return this.cMapper.findAllCategory(orgId);
	}

	@Override
	public Category findOneCategory(long id) {
		return this.cMapper.findOneCategory(id);
	}

	@Override
	public void deleteOne(long id) {
		this.cMapper.deleteOne(id);

	}

	@Override
	public void createCategory(Category category) {
		this.cMapper.createCategory(category);

	}

	@Override
	public void updateCategory(Category category) {
		this.cMapper.updateCategory(category);

	}

	@Override
	public boolean exists(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

}
