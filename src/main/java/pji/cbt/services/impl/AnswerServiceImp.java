package pji.cbt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Answer;
import pji.cbt.mapper.AnswerMapper;
import pji.cbt.services.AnswerService;

@Service
public class AnswerServiceImp implements AnswerService {
	
	@Autowired
	private AnswerMapper aMapper;
	
	@Override
	public List<Answer> findAllAnswer() {
		return aMapper.findAllAnswer();
	}

	@Override
	public Answer findOne(int idAnswer) {
		return aMapper.findOne(idAnswer);
	}

	@Override
	public void deleteAnswer(int id) {
		aMapper.deleteAnswer(id);
		
	}
	
	public void createAnswer(Answer answer){
		aMapper.createAnswer(answer);
	}

	@Override
	public List<Answer> findAnswerByQuestion(int idQuestion) {
		return aMapper.findAnswerByQuestion(idQuestion);
	}
}
