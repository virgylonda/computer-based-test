package pji.cbt.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/rest/tester")
public class TesterRestController {
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private QuestionService quesSvc;
	
	@Autowired
	private AnswerService ansSvc;

	@Autowired
	private CategoryService ctgSvc;
	
	@Autowired
	private TestUserService testSvc;
	
	public TesterRestController(){
	}
	
	/**
	 * View List of Category Question
	 * @return findAllCategory
	 */
	@RequestMapping(path="/category/list",method=RequestMethod.GET)
	public List<Category> findAllCategory(){
		return ctgSvc.findAllCategory();
	}
	
    /**
     * Create 
     * @param category
     * @param ucBuilder
     * @return New Category Question HttpStatus.CREATED
     */
    @RequestMapping(value = "/testers/addcategory", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Category " + category.getDescription());

    	try {
    		ctgSvc.createCategory(category);
    	}catch (Exception e) {
    		System.out.println(e);
		}
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/testers/category/{id}").buildAndExpand(category.getIdCategory()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
    /**
     * Update
     * @param id
     * @param category
     * @return  Category Question  HttpStatus.OK
     */
    @RequestMapping(value = "/testers/updatecategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        System.out.println("Updating Category " + id);
         
        Category currentCategory = ctgSvc.findOneCategory(id);
         
        if (currentCategory==null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        
        currentCategory.setQuestionCategory(category.getQuestionCategory());
        currentCategory.setDescription(category.getDescription());
        currentCategory.setQuestionType(category.getQuestionType());
        currentCategory.setTimeLimit(category.getTimeLimit());
                    
        ctgSvc.updateCategory(currentCategory);
        return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }
	
    /**
     * Delete by Category Question
     * @param id
     * @return HttpStatus.NO_CONTENT
     */
    @RequestMapping(value = "/testers/deletecategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Category with id " + id);
 
        Category category = ctgSvc.findOneCategory(id);
        if (category == null) {
            System.out.println("Unable to delete. Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
 
        ctgSvc.deleteOne(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
	
    
	/**
	 * View List of Question by Category Question
	 * @param id
	 * @return findAllQuestionByCategory
	 */
	@RequestMapping(path="/question/list/{id}",method=RequestMethod.GET)
	public List<Question> findAllQuestionByCategory(@PathVariable int id){
		return quesSvc.findAllQuestionByCategory(id);
	}
 
    /**
     * Create Question by id Question and Category Question
     * @param question
     * @param ucBuilder
     * @return Question HttpStatus.CREATED
     */
    @RequestMapping(value = "/testers/addquestion", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Question " + question.getQuestion());

    	try {
    		quesSvc.createQuestion(question);
    	}catch (Exception e) {
    		System.out.println(e);
		}
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/testers/question/{id}").buildAndExpand(question.getIdQuestion()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	/**
	 * Update  by id Question and Category Question
	 * @param id
	 * @param question
	 * @return Question HttpStatus.OK
	 */
	@RequestMapping(path = "/question/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Question> formEditQuestion(@PathVariable("id") int id, @RequestBody Question question) {
    System.out.println("Updating Question " + id);
        
        
        Question currentQuestion = quesSvc.findOneQuestion(id);
        List<Answer> answers = ansSvc.findAnswerByQuestion(id);
         
        if (currentQuestion==null) {
            System.out.println("Question with id " + id + " not found");
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }
        currentQuestion.setQuestion(question.getQuestion());
                    
        quesSvc.editQuestion(currentQuestion);
        return new ResponseEntity<Question>(currentQuestion, HttpStatus.OK);
    }
	
    /**
     * Delete  by  id Question and Category Question
     * @param id
     * @return Question HttpStatus.NOT_FOUND
     */
    @RequestMapping(value = "/testers/deletequestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Question with id " + id);
 
        Question question = quesSvc.findOneQuestion(id);
        if (question == null) {
            System.out.println("Unable to delete. Question with id " + id + " not found");
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }
 
        quesSvc.deleteQuestion(id);
        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }
	
    /**
     * View List of Result Score User 
     * @param id
     * @return findUserTestById
     */
    @RequestMapping(value = "/testers/scores/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestUser> getAllScoresByUsers2(@PathVariable("id") int id) {
        System.out.println("Fetching Scores with user id " + id);
        TestUser testUser = testSvc.findUserTestById(id);
        return new ResponseEntity<TestUser>(testUser, HttpStatus.OK);
    }
    
	
	/**
	 * View List of  Detail Result Score User by id user_test
	 * @param userId
	 * @return findTestByUserIdDetail
	 */
	@RequestMapping(path="user/score/detail/{userId}",method=RequestMethod.GET)
	public List<TestUser> findTestByUserId(@PathVariable int userId){
		return testSvc.findTestByUserId(userId);
	}
   
	/**
	 * View List of Assignment User
	 * @return findAllAssignment
	 */
	@RequestMapping(path="/user/assignment",method=RequestMethod.GET)
	public List<User> findAllAssignment(){
		return userSvc.findAllUser(3);
	}
    
    /**
     * View List Assignment User by id_user
     * @param id
     * @return findTestAssignmentById
     */
    @RequestMapping(value = "/testers/assignment/list/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestUser> findTestAssignmentById(@PathVariable("id") int id) {
        System.out.println("Fetching Assignment with user id " + id);
        return testSvc.findTestAssignment(id);
    }
	

	/**
	 * List Category Detail by id
	 * @param id
	 * @param redirectAttributes
	 * @param model
	 * @return findOneCategory
	 */
	@RequestMapping(path = "/category/edit/{id}", method=RequestMethod.GET)
	public Category findOneCategory(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		Category category = this.ctgSvc.findOneCategory(id);
		model.addAttribute("data", category);
		return category;
	}
	
}