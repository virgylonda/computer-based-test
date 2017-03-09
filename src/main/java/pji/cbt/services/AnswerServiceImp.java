package pji.cbt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Answer;
import pji.cbt.iservices.AnswerService;
import pji.cbt.mapper.AnswerMapper;

@Service
public class AnswerServiceImp implements AnswerService {
	
	@Autowired
	private AnswerMapper aMapper;
	
	@Override
	public List<Answer> findAllAnswer() {
		return aMapper.findAllAnswer();
	}

	@Override
	public Answer findOne(int questionId) {
		return aMapper.findOne(questionId);
	}

	@Override
	public void deleteAnswer(int id) {
		aMapper.deleteAnswer(id);
		
	}
}
