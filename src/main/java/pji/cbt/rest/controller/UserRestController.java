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

import org.springframework.beans.factory.annotation.Autowired;
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

	public double calculateScore(double point, double quest) {
		double score = (point / quest) * 100;
		score = Math.round(score * 100);
		score = score / 100;
		return score;
	}

	
	/**
	 * View List of Test
	 * @param userId
	 * @return findTestHaveAssign
	 */
	@RequestMapping(path = "/test/list/{userId}", method = RequestMethod.GET)
	public List<TestUser> findTestHaveAssign(@PathVariable int userId){
		return tstSvc.findTestHaveAssign(userId);
	}
	
    /**
     * View List User have assign
     * @param id
     * @return getAllAssignTestUser
     */
    @RequestMapping(value = "/users/assign/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestUser> getAllAssignTestUser(@PathVariable("id") int id) {
        System.out.println("Fetching Assign Test with user id " + id);
        return tstSvc.findTestHaveAssign(id);
    }
    
    /**
     * Edit Profile User
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userSvc.findOne(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setPassword(user.passwordToHash(user.getPassword()));
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail()); 
        
        userSvc.updateUser(currentUser);
        userSvc.updatePassword(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

}