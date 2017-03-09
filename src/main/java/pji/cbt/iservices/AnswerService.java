package pji.cbt.iservices;

import java.util.List;

import pji.cbt.entities.Answer;

public interface AnswerService {

	public List<Answer> findAllAnswer();
	
	public Answer findOne();
	
	public void deleteAnswer();
}
