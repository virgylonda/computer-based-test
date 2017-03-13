package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.Question;

public interface QuestionService {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int categoryId);
	
	public void deleteQuestion(int id);
	
	public void createQuestion(Question question);
}
