package pji.cbt.rest.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.el.stream.Stream;
import org.apache.log4j.Logger;
import org.mockito.internal.util.collections.ArrayUtils;
import org.mockito.internal.util.collections.Iterables;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;


@RestController
@RequestMapping("/test")
public class TestRestController {
	
	@Autowired
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private TestUserService testSvc;
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private QuestionService quesSvc;
	
	@Autowired 
	private AnswerService ansSvc;
	
	
	public TestRestController(){
	}
	
	private static Logger logger = Logger.getLogger(TestRestController.class);
	 
	  /**
			 * @param  		-
			 * @method		GET
			 * @return      view all user for assignment
			 */
		    @RequestMapping(path = "/getallassignedtest", method=RequestMethod.GET)
			public List<User> getAllUserforAssignment(){
				return userSvc.findAllUser(3);
			}
		    
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      view test assignment by id
			 */
		    @RequestMapping(value = "/gettesthaveassignbyuserid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		    public List<TestUser> getTestHaveAssignByUserId (@PathVariable("id") int id) {
		    	logger.info("Fetching Assignment with user id " + id);
		        return testSvc.findTestAssignment(id);
		    }
	
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      get a test by user id
			 */
		    @RequestMapping(value = "/getallscorebyuserid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		    public List<TestUser> getAllScoreByUserTestId(@PathVariable("id") int id) {
		    	logger.info("Fetching Scores with user id " + id);
		        return testSvc.findTestByUserId(id);
		    }

		    
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      get test have assign by user id
			 */	
		    @RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
			public List<TestUser> getAllTestHaveAssign(@PathVariable("id") int id) {
		    	logger.info("Fetching Test Have Assign " +id);
				return testSvc.findTestHaveAssign(id);
			}
		   
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      get test have assign by user id
			 */
		    @RequestMapping(path = "/dotest/{idcategory}", method = RequestMethod.GET)
		    public List<Question> getAllQuestionForTest(@PathVariable("idcategory") int idcategory, TestUser testUser, Timestamp timestamp){
		    	logger.info("Fetching All Question by Category "+idcategory);
		    	List<Question> listQuest = new ArrayList<Question>();
		    	listQuest = quesSvc.findQuestionRandomOrder(idcategory);
		    	
				Collections.shuffle(listQuest);
				
				return listQuest;
		    }
		      
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      get answer by question id (shuffle answer)
			 */
		    @RequestMapping(value = "/getanswer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		    public List<Answer> getAnswerByIdQuestion(@PathVariable("id") int id) {
		    	logger.info("Fetching Answer with Question id : " + id);
		    	List<Answer> listAnswer = ansSvc.findAnswerByQuestion(id);
		    	Collections.shuffle(listAnswer);
		    
		        return listAnswer;
		    }
		    
		    /**
			 * @param  		id
			 * @method		PUT
			 * @return      begin test, update begin date and status
			 */
		    @RequestMapping(value = "/begintest/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<TestUser> beginTestUser(@PathVariable("id") int id, Timestamp timestamp) {
		    	logger.info("Fetching Test User with ID : " + id);
		    	TestUser testUser = testSvc.findUserTestById(id);
		    	
		    	if(testUser.getStarted() == null) {
		    		timestamp = new Timestamp(System.currentTimeMillis());
			    	String startTest = sdf.format(timestamp);
					try {
						Date date = sdf.parse(startTest);
						testUser.setStarted(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
			    	testUser.setStatus("Done");
			    	
			    	testSvc.updateStartTest(testUser);
			    	return new ResponseEntity<TestUser>(testUser, HttpStatus.OK);
				}else{
					logger.warn("Test has been done...");
					return new ResponseEntity(HttpStatus.BAD_REQUEST);
				}
		        
		    }
		    
		    /**
			 * @param  		id
			 * @method		PUT
			 * @return      submit test, update end date and score
			 */
		    @RequestMapping(value = "/submittest/{id}", method = RequestMethod.PUT)
		    public ResponseEntity<TestUser> submitTest(@PathVariable("id") int id, @RequestBody TestUser userTest, Timestamp timestamp) {
		    	logger.debug("Submitting test " + id);
		    	TestUser testUser = testSvc.findUserTestById(id);
		    	
		    	if(testUser.getEnded() == null) {
		    		timestamp = new Timestamp(System.currentTimeMillis());
			    	String startTest = sdf.format(timestamp);
					try {
						Date date = sdf.parse(startTest);
						testUser.setEnded(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					testUser.setScore(userTest.getScore());
					
					testSvc.updateEndTest(testUser);
			        
			        return new ResponseEntity<TestUser>(testUser, HttpStatus.OK);
		    	}else{
		    		logger.warn("Error submiting test");
		    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
		    	}
		        
		    }
		    
		    /**
			 * @param  		-
			 * @method		GET
			 * @return      view all user for showing score
			 */
		    @RequestMapping(path = "/getalluserscore", method=RequestMethod.GET)
			public List<TestUser> getAllUsersScore(){
				return testSvc.findUserSummaryScore();
			}
		    
}