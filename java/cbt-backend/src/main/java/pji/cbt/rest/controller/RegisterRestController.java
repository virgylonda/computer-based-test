package pji.cbt.rest.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.services.UserService;


@RestController
@RequestMapping("/rest")
public class RegisterRestController {
	@Autowired
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final String SUCCESS_RESULT="<result>Success</result>";
	private static final String FAILURE_RESULT="<result>Failure</result>";

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userSvc;
	
	private static Logger logger = Logger.getLogger(UserRestController.class);
	
	      
	 /**
		 * @param  		-
		 * @method		POST
		 * @return      create a new Tester (Register)
		 */
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user, Roles role, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating User : "+user.getUserId());
	        
	        if(userSvc.exists(user)){
	        	logger.info("a user with id " + user.getUsername() + " already exists");
	        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	    	user.setPassword(user.passwordToHash(user.getPassword()));
	    	userSvc.createUser(user);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/rest/user/getuserbyid/{id}").buildAndExpand(user.getUserId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	    

	    
}
    
    
    
    
    
    
    
    
    
    
    
  
