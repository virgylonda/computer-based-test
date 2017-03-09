package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Answer;

@Mapper
public interface AnswerMapper {

	public List<Answer> findAllAnswer();
	
	public Answer findOne();
	
	public void deleteAnswer();
}
