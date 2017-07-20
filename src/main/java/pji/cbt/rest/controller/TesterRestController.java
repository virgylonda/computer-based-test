package pji.cbt.rest.controller;

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
import pji.cbt.form.FormAssignment;
import pji.cbt.form.FormQuestion;
import pji.cbt.services.AnswerService;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.TestUserService;
import pji.cbt.services.UserService;

@RestController
@RequestMapping("/alltester")
public class TesterRestController {
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private TestUserService testSvc;

	public TesterRestController(){
	}
	private static Logger logger = Logger.getLogger(TesterRestController.class);
	
	

	@RequestMapping(path = "/getalltester", method=RequestMethod.GET)
	public List<User> getAllTester(){
		return userSvc.findAllUser(2);
	}
	
	/**
     * Create User Test by id User and id Categor
     * @param testUser
     * @param ucBuilder
     * @method POST
     * @return TestUser HttpStatus.CREATED
     */
    @RequestMapping(value = "/assignment/save", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserTest(@RequestBody TestUser testUser, UriComponentsBuilder ucBuilder) {
     logger.info("Creating User Test " + testUser.getCategories());

     try {
      testSvc.saveTest(testUser);
     }catch (Exception e) {
      logger.error(e);
     }
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/testers/scores/{id}").buildAndExpand(testUser.getTestId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}