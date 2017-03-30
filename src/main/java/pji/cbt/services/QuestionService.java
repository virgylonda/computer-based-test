package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.Question;

public interface QuestionService {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int idQuestion);
	
	public void updateOrderingQuestion(int category, int ordering);
	
	public Question findAllQuestionByCategoryLimit(int idCategory, int limit, int offset);
	
	public void deleteQuestion(int idQuestion);
	
	public void createQuestion(Question question);

	public void editQuestion(Question question);
	
	public List<Question> findAllQuestionByCategory(int idCategory);
}
