package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
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
     * Create 
     * @param 	answer
     * @param 	ucBuilder
     * @method	POST
     * @return 	New Answer HttpStatus.CREATED
     */
    @RequestMapping(value = "/createanswer", method = RequestMethod.POST)
    public ResponseEntity<Answer> createCategory(@RequestBody Answer answer, UriComponentsBuilder ucBuilder) {
    	logger.info("Creating answer " + answer.getAnswer());
    	
    	if(ansSvc.answerExists(answer)){
    		logger.info("a answer with id " + answer.getIdAnswer() + " already exists");
    		return new ResponseEntity<Answer>(HttpStatus.CONFLICT);
    	}
    	
    	ansSvc.createAnswer(answer);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setLocation(ucBuilder.path("/answer/detail/{id}").buildAndExpand(answer.getIdAnswer()).toUri());
    	
        return new ResponseEntity<Answer>(answer, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteAnswerById(@PathVariable("id") int id) {
    	logger.info("Fetching & Deleting Answer with id " + id);
 
        Answer answer = ansSvc.findOne(id);
        
        if (answer == null) {
        	logger.info("Unable to delete. Answer with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
 
        ansSvc.deleteAnswer(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    /**
	 * @param  		id
	 * @method		GET
	 * @return      find one answer
	 */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Answer> getOneAnswerById(@PathVariable("id") int id) {
    	logger.info("Fetching answer with id : " + id);
    	Answer answer = ansSvc.findOne(id);
    	
    	if(answer == null){
    		logger.warn("answer with id "+ id + " not found");
    		return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<Answer>(answer, HttpStatus.OK);
    }
    
    /**
     * Update
     * @param   id
     * @param   answer
     * @method	PUT
     * @return  update answer  HttpStatus.OK
     */
    @RequestMapping(value = "/updateanswer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
    	logger.info("Updating Answer" + id);
        
    	Answer currentAnswer = ansSvc.findOne(id);
         
        if(currentAnswer == null){
        	logger.warn("Answer with id " + id + "not found");
        	return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
        }
        
        currentAnswer.setOrderingAnswer(answer.getOrderingAnswer());
        currentAnswer.setAnswer(answer.getAnswer());
        currentAnswer.setCorrectAnswer(answer.isCorrectAnswer());
                    
        ansSvc.editAnswer(currentAnswer);
        return new ResponseEntity<Answer>(currentAnswer, HttpStatus.OK);
    }
		
}

