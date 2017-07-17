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
			public List<TestUser> getAllTestHaveAssign(HttpServletRequest request, @PathVariable("id") int id) {
		    	logger.info("Fetching Test Have Assign " +id);
				return testSvc.findTestHaveAssign(id);
			}
		    
		    /**
			 * @param  		id
			 * @method		GET
			 * @return      get test have assign by user id
			 */
		    @RequestMapping(path = "/dotest/{idcategory}", method = RequestMethod.GET)
		    public Map<String, List<Question>> getAllQuestionForTest(HttpServletRequest request, @PathVariable("idcategory") int idcategory, TestUser testUser, Timestamp timestamp){
		    	logger.info("Fetching All Question by Category "+idcategory);
		    	Question question = new Question();
		    	List<Question> listQuest = new ArrayList<Question>();
		    	List<Answer> listAnswer = new ArrayList<Answer>();
		    	listQuest = quesSvc.findQuestionRandomOrder(idcategory);
		    	
		    	for (Question q : listQuest) {
					//List<Answer> listAnswer = new ArrayList<Answer>();
					listAnswer.addAll(ansSvc.findAnswerByQuestion(q.getIdQuestion()));
					Collections.shuffle(listAnswer);
				}
		    	
				Collections.shuffle(listQuest);
				Map<String,List<Question>> map =new HashMap();
				map.put("question",listQuest);
				return map;
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
