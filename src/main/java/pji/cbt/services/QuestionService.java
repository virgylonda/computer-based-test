package pji.cbt.services;

import java.util.List;

import pji.cbt.entities.Question;

public interface QuestionService {

	public List<Question> findAllQuestion();
	
	public Question findOneQuestion(int idQuestion);
	
	public Question findAllQuestionByCategoryLimit(int idCategory, int limit, int offset);
	
	public void updateOrderingQuestion(int category, int ordering);
	
	public Question findCountQuestion(int idCategory);
	
	public List<Question> findQuestionRandomOrder(int idCategory);
	
	public void deleteQuestion(int idQuestion);
	
	public void createQuestion(Question question);

	public void editQuestion(Question question);
	
	public List<Question> findAllQuestionByCategory(int idCategory);
}
