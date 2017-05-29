package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pji.cbt.entities.Question;

@Mapper
public interface QuestionMapper {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int idQuestion);
	
	public Question findAllQuestionByCategoryLimit(@Param("idCategory")int idCategory,@Param("limit") int limit,@Param("offset") int offset);
	
	public void updateOrderingQuestion(@Param("category") int category, @Param("ordering") int ordering);
	
	public Question findCountQuestion(int idCategory);
	
	public List<Question> findQuestionRandomOrder(int idCategory);
	
	public void deleteQuestion(int idQuestion);
	
	public void createQuestion(Question question);
	
	public void editQuestion(Question question);
	
	public List<Question> findAllQuestionByCategory(int idCategory);
	
}
