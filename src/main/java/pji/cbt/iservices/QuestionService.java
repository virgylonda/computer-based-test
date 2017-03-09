package pji.cbt.iservices;

import java.util.List;

import pji.cbt.entities.Question;

public interface QuestionService {

	public List<Question> findAllQuestion();
	
	public Question findOne();
	
	public void deleteQuestion();
	
	public void createQuestion();
}
