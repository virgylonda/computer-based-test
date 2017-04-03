package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Question;
import pji.cbt.mapper.QuestionMapper;
import pji.cbt.services.QuestionService;

@Service
public class QuestionServiceImp implements QuestionService {
	
	@Autowired
	private QuestionMapper qMapper;

	@Override
	public List<Question> findAllQuestion() {
		return qMapper.findAllQuestion();
	}
	
	@Override
	public List<Question> findAllQuestionByCategory(int idCategory) {
		return qMapper.findAllQuestionByCategory(idCategory);
	}

	
	@Override
	public Question findOneQuestion(int idQuestion) {
		return qMapper.findOneQuestion(idQuestion);
	}

	@Override
	public void deleteQuestion(int idQuestion) {
		qMapper.deleteQuestion(idQuestion);
		
	}

	@Override
	public void createQuestion(Question question) {
		qMapper.createQuestion(question);
	}

	@Override
	public void editQuestion(Question question) {
		qMapper.editQuestion(question);
		
	}

	@Override
	public Question findAllQuestionByCategoryLimit(int idCategory, int limit, int offset) {
		return qMapper.findAllQuestionByCategoryLimit(idCategory, limit, offset);
	}

	@Override
	public Question findCountQuestion(int idCategory) {
		return qMapper.findCountQuestion(idCategory);
	}

	@Override
	public void updateOrderingQuestion(int category, int ordering) {
		qMapper.updateOrderingQuestion(category, ordering);
	}

	
	
}