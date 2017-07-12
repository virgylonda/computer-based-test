package pji.cbt.rest.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.form.FormAnswer;
import pji.cbt.form.FormQuestion;
import pji.cbt.services.AnswerService;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;


@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Autowired
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	HttpSession session;

	@Autowired
	private TestUserService tstSvc;

	@Autowired
	private QuestionService queSvc;
	
	@Autowired
	private CategoryService catSvc;

	@Autowired
	private AnswerService ansSvc;

	@Autowired
	private UserService userSvc;
	
	private static Logger logger = Logger.getLogger(UserRestController.class);
	
	public double calculateScore(double point, double quest) {
		double score = (point / quest) * 100;
		score = Math.round(score * 100);
		score = score / 100;
		return score;
	}


  
    
    
    /**
	 * @param  		-
	 * @method		GET
	 * @return      list of all Actors (Admin, User, and Tester)
	 */
	@RequestMapping(value = "/getallactor", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userSvc.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

	
	
	 /**
	 * @param  		-
	 * @method		GET
	 * @return      list of all Users
	 */
	@RequestMapping(path = "/getAllUser", method=RequestMethod.GET)
	public List<User> findAllUser(){
		return userSvc.findAllUser(3);
	}
	
	
	 /**
	  * @param  	id
	  * @method		GET
	  * @return     retrieve a all actor by id
	  */
	 @RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<User> getUser(@PathVariable("id") long id) {
	    logger.info("Fetching user with id : "+id);
	    User user = userSvc.findOne(id);
	      if (user == null) {
	          logger.warn("User with id " + id + " not found");
	          return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	      
	
	 /**
		 * @param  		-
		 * @method		POST
		 * @return      create a new user
		 */
	    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user, Roles role, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating User : "+user.getUsername());
	        String password = user.getPassword();

	    	try {
	    		user.setPassword(user.passwordToHash(user.getPassword()));
	    		userSvc.createUser(user);
	    	}catch (Exception e) {
	    		logger.info(e);
				user.setPassword(password);
			}
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	/**
	 * Update  	by id
	 * @param 	id
	 * @method	PUT
	 * @return 	tester and user account HttpStatus.OK
	 */
	    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateAdmin(@PathVariable("id") long id, @RequestBody User user) {
	        logger.info("Updating Admin " + id);  
	        User currentUser = userSvc.findOne(id);
	         
	        if (currentUser==null) {
	        	logger.warn("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        
//	        currentUser.setPassword(user.passwordToHash(user.getPassword()));
	        currentUser.setName(user.getName());
	        currentUser.setEmail(user.getEmail()); 
	        
	        userSvc.updateUser(currentUser);
//	        userSvc.updatePassword(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	
	    /**
		 * @param  		id
		 * @method		DELETE
		 * @return      delete a user by id
		 */
	    @RequestMapping(value = "/deleteuserbyid/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
	    	logger.info("Fetching & Deleting User with id "+id); 
	 
	        User user = userSvc.findOne(id);
	        if (user == null) {
	        	logger.warn("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userSvc.deleteOne(id);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
}
    
    
    
    
    
    
    
    
    
    
    
  