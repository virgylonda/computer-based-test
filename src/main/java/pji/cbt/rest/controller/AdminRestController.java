package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pji.cbt.entities.Answer;
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.UserService;


@RestController
@RequestMapping("/rest/admin" )
public class AdminRestController {
	@Autowired
	HttpSession session; 
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private QuestionService quesSvc;
	
	@Autowired
	private AnswerService ansSvc;

	public AdminRestController() {
	}

	/**
	 * View tester account
	 * @return AllTester
	 */
	@RequestMapping(path = "/tester/list", method=RequestMethod.GET)
	public List<User> findAllUser(){
		return userSvc.findAllUser(2);
	}
	
	/**
	 * View user account
	 * @param id
	 * @return user HttpStatus.OK
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userSvc.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
	/**
	 * Create account
	 * @param user
	 * @param role
	 * @param ucBuilder
	 * @return tester and user HttpStatus.CREATED
	 */
	@RequestMapping(path="/users/createnew", method= RequestMethod.POST)
	 public ResponseEntity<Void> createUser(@RequestBody User user, Roles role, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
        
        String password = user.getPassword();

    	try {
    		user.setPassword(user.passwordToHash(user.getPassword()));
    		userSvc.createUser(user);
    	}catch (Exception e) {
    		System.out.println(e);
			user.setPassword(password);
		}
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	/**
	 * Update  by id
	 * @param id
	 * @param user
	 * @return tester and user account HttpStatus.OK
	 */
	@RequestMapping(path = "/tester/edit/{id}", method=RequestMethod.PUT)
	 public ResponseEntity<User> editTester(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating Tester " + id);
         
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
	
	/**
	 * Delete account
	 * @param id
	 * @return tester and user delete HttpStatus.NO_CONTENT
	 */
	@RequestMapping(path = "/tester/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteTester(@PathVariable long id) {
		
		 System.out.println("Fetching & Deleting User with id " + id);
		 User user = userSvc.findOne(id);	 
		 if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userSvc.deleteOne(id);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * List of Questions
	 * @return findAllQuestion
	 */
	@RequestMapping(path="/tester/listquestions",method=RequestMethod.GET)
	public List<Question> findAllQuestion(){
		return quesSvc.findAllQuestion();
	}
	

	/**
	 * List of Answers
	 * @return findAllAnswer
	 */
	@RequestMapping(path="/tester/listanswers", method=RequestMethod.GET)
	public List<Answer> findAllAnswer(){
		return ansSvc.findAllAnswer();
	}

	
	
	/**
	 * Delete Question by question id
	 * @param id
	 * @param attributes
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/question/delete/{question_id}",method=RequestMethod.GET)
	public String deleteQuestion(@PathVariable int id,RedirectAttributes attributes,Model model){
		try{
			this.quesSvc.deleteQuestion(id);
			}catch (Exception e) {
			attributes.addFlashAttribute("msgerror","Delete Question Failed! Try Again");
			return"redirect:/index_question";
		}
			attributes.addFlashAttribute("msgsuccess","Delete Question Success");
			return "redirect:/index_question";
		}
	
	/**
	 * Delete Answer by answer id
	 * @param id
	 * @param attributes
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/answer/delete/{question_id}",method=RequestMethod.GET)
	public String deleteAnswer(@PathVariable int id,RedirectAttributes attributes,Model model){
		try{
			this.ansSvc.deleteAnswer(id);
		}catch (Exception e){
			attributes.addFlashAttribute("msgerror","Delete Question Failed! Try Again");
			return "redirect:/index_answer";
		}
		attributes.addFlashAttribute("msgsuccess","Delete Question Success");
			return "redirect:/index_answer";
	}
	

	/**
	 * Delete Answer
	 * @param questionId
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/answer/delete/{id}",method=RequestMethod.GET)
	public String findOneAnswer(int questionId ,Model model){
		model.addAttribute("answer", this.ansSvc.findOne(questionId));
		return "";
		
	}
	
}
