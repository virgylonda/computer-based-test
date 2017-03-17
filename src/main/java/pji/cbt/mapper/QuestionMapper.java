package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Question;

@Mapper
public interface QuestionMapper {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int idQuestion);
	
	public void deleteQuestion(int idQuestion);
	
	public void createQuestion(Question question);
	
	public List<Question> findAllQuestionByCategory(int idCategory);
}
