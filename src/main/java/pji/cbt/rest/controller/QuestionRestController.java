package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Question;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionRestController {

	@Autowired
	HttpSession session; 
	
	@Autowired
	private QuestionService quesSvc;
	
	@Autowired
	private AnswerService ansSvc;
	
	public QuestionRestController(){
	}
	private static Logger logger = Logger.getLogger(QuestionRestController.class);
	

	
	 /**
     * Create Question by id Question and Category Question
     * @param	question
     * @param	ucBuilder
     * @method	POST
     * @return	Question HttpStatus.CREATED
     */
    @RequestMapping(value = "/createquestion", method = RequestMethod.POST)
    public ResponseEntity<Question> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
    	logger.info("Creating Question " + question.getQuestion());

    	try {
    		quesSvc.createQuestion(question);
    	}catch (Exception e) {
    		logger.info(e);
		}
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/question/{id}").buildAndExpand(question.getIdQuestion()).toUri());
        return new ResponseEntity<Question>(question, HttpStatus.CREATED);
    }
	
    /**
	   * @param  	id
	   * @method	PUT
	   * @return    update a question
	   */
	    @RequestMapping(value = "/updatequestion/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
	    	logger.info("Updating Question " + id);
	        
	        
	        Question currentQuestion = quesSvc.findOneQuestion(id);
	        List<Answer> answers = ansSvc.findAnswerByQuestion(id);
	         
	        if (currentQuestion==null) {
	        	logger.info("Question with id " + id + " not found");
	            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
	        }
	        currentQuestion.setQuestion(question.getQuestion());
	                    
	        quesSvc.editQuestion(currentQuestion);
	        return new ResponseEntity<Question>(currentQuestion, HttpStatus.OK);
	    }
	
  /**
   * Delete by  id Question and Category Question
   * @param	id
   * @method	DELETE
   * @return	Question HttpStatus.NOT_FOUND
   */
  @RequestMapping(value = "/deletequestion/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Question> deleteQuestionbyid(@PathVariable("id") int id) {
  	logger.info("Fetching & Deleting Question with id " + id);
		 

      Question question = quesSvc.findOneQuestion(id);
      if (question == null) {
    	  logger.info("Unable to delete. Question with id " + id + " not found");
          return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
      }

      quesSvc.deleteQuestion(id);
      return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
  }
  
  /**
	 * @param  		-
	 * @method		GET
	 * @return      list all question
	 */
  @RequestMapping(path = "/getallquestion", method=RequestMethod.GET)
	public List<Question> getAllQuestion(){
		return quesSvc.findAllQuestion();
	}
  
  /**
	 * View List of Question by Category Question
	 * @param	id
	 * @method	GET
	 * @return	findAllQuestionByCategory
	 */
	@RequestMapping(path="/getallquestionbycategoryid/{id}",method=RequestMethod.GET)
	public List<Question> getAllQuestionByCategoryId (@PathVariable int id){
		logger.info("Fetching Question with id " + id);
		return quesSvc.findAllQuestionByCategory(id);
	}
	
	/**
     * @param   idQuestion
     * @method  GET
     * @return  question
     */
   @RequestMapping(value = "/getdetailquestion/{id}", method = RequestMethod.GET)
   public ResponseEntity<Question> getQuestionById(@PathVariable("id") int id) {
    Question question = quesSvc.findOneQuestion(id);
    return new ResponseEntity<Question>(question, HttpStatus.OK);
   }
   
}
