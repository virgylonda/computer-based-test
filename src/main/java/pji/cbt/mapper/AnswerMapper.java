package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Answer;

@Mapper
public interface AnswerMapper {

	public List<Answer> findAllAnswer();
	
	public Answer findOne(int idAnswer);
	
	public List<Answer> findAnswerByQuestion(int idQuestion);
	
	public void deleteAnswer(int id);
	
	public void createAnswer(Answer answer);
}
