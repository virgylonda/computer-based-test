package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Question;

@Mapper
public interface QuestionMapper {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int categoryId);
	
	public void deleteQuestion(int id);
	
	public void createQuestion(Question question);
}
