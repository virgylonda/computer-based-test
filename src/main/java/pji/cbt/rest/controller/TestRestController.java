package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;


@RestController
@RequestMapping("/test")
public class TestRestController {
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private TestUserService testSvc;
	
	@Autowired
	private UserService userSvc;
	
	
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
			 * @param  		-
			 * @method		GET
			 * @return      view all user for showing score
			 */
		    @RequestMapping(path = "/getalluserscore", method=RequestMethod.GET)
			public List<TestUser> getAllUsersScore(){
				return testSvc.findUserSummaryScore();
			}
}
