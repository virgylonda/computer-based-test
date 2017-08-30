package pji.cbt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pji.cbt.entities.Category;

@Mapper
public interface CategoryMapper {

	public abstract List<Category> findAllCategory(@Param("orgId") long orgId);
	
	public abstract Category findOneCategory(long id);
	
	public abstract void deleteOne(long id);

	public abstract void createCategory(Category category);
	
	public void updateCategory(Category category);
}
