package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Question;

@Mapper
public interface QuestionMapper {

public List<Question> findAllQuestion();
	
	public Question findOne();
	
	public void deleteQuestion();
	
	public void createQuestion();
}
