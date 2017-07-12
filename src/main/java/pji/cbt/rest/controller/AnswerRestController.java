package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Question;
import pji.cbt.services.AnswerService;

@RestController
@RequestMapping("/answer")


public class AnswerRestController {
	@Autowired
	HttpSession session; 
	
	@Autowired
	private AnswerService ansSvc;

public AnswerRestController() {
		
	}
	private static Logger logger = Logger.getLogger(AnswerRestController.class);

	
	/**
	 * List of 	Answers
	 * @method	GET
	 * @return	getAllAnswer
	 */
	@RequestMapping(path="(/getallanswer", method=RequestMethod.GET)
	public List<Answer> findAllAnswer(){
		return ansSvc.findAllAnswer();
	}
	
		
		
		/**
		 * List of 	Answers by Id
		 * @method	GET
		 * @return	getAnswerById
		 */
	@RequestMapping(path="(/getAnswerById/{id}", method=RequestMethod.GET)
		public ResponseEntity <Answer> findOne(@PathVariable int idAnswer){
		Answer answer = ansSvc.findOne(idAnswer);
		return new ResponseEntity<Answer>(answer, HttpStatus.OK);
		
		}
	
	/**
	 * Delete of 	Answers by Id
	 * @method	GET
	 * @return	deleteAnswerById 
	 */
		
}

