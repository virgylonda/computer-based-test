package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Question;
import pji.cbt.entities.TestUser;
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
	@RequestMapping(path= "/getallanswer", method=RequestMethod.GET)
	public List<Answer> getAllAnswer(){
		return ansSvc.findAllAnswer();
	}
		
		
		/**
		 * List	Answers by Id Question
		 * @method	GET
		 * @return	getAnswerById Question
		 */
	@RequestMapping(path= "/getanswerbyid/{id}", method=RequestMethod.GET)
		public List<Answer> getAnswerById(@PathVariable int id){
		logger.info("Fetching Question with id " + id);
		return ansSvc.findAnswerByQuestion(id);
		
		}

	
	 /**
     * Delete by id Answer
     * @param	id
     * @method	DELETE
     * @return	Answer HttpStatus.NOT_FOUND
     */
    @RequestMapping(value = "/deleteanswerbyid/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Answer> deleteAnswerById(@PathVariable("id") int id) {
    	logger.info("Fetching & Deleting Answer with id " + id);
		 
 
        Answer answer = ansSvc.findOne(id);
        if (answer == null) {
        	logger.info("Unable to delete. Answer with id " + id + " not found");
            return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
        }
 
        ansSvc.deleteAnswer(id);
        return new ResponseEntity<Answer>(HttpStatus.NO_CONTENT);
    }
    
    /**
	 * @param  		id
	 * @method		GET
	 * @return      get answer by question id
	 */
    @RequestMapping(value = "/getanswer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Answer> getAnswerByIdQuestion(@PathVariable("id") int id) {
    	logger.info("Fetching Answer with Question id : " + id);
        return ansSvc.findAnswerByQuestion(id);
    }
		
}

