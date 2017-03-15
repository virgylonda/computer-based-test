package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.Answer;

public interface AnswerService {

	public List<Answer> findAllAnswer();
	
	public Answer findOne(int questionId);
	
	public void deleteAnswer(int id);
	
	public void createAnswer(Answer answer);
}
