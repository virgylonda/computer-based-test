package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.Answer;

public interface AnswerService {

	public List<Answer> findAllAnswer();
	
	public Answer findOne(int idAnswer);
	
	public List<Answer> findAnswerByQuestion(int idQuestion);
	
	public void deleteAnswer(int id);
	
	public void createAnswer(Answer answer);

	public void editAnswer(Answer answer);
}
