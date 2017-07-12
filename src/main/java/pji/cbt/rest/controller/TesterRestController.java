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
@RequestMapping("/rest/tester")
public class TesterRestController {
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private UserService userSvc;
	

	public TesterRestController(){
	}
	private static Logger logger = Logger.getLogger(TesterRestController.class);
	
	/**
<<<<<<< HEAD
	 * View tester account
=======
	 * View List of Category Question
	 * @method	GET
	 * @return	ListAllCategory
	 */
	@RequestMapping(path="/category/list",method=RequestMethod.GET)
	public List<Category> findAllCategory(){
		return ctgSvc.findAllCategory();
	}
	
    /**
     * Create 
     * @param 	category
     * @param 	ucBuilder
     * @method	POST
     * @return 	New Category Question HttpStatus.CREATED
     */
    @RequestMapping(value = "/testers/addcategory", method = RequestMethod.POST)
    public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
    	logger.info("Creating Category " + category.getDescription());

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
     * @param   id
     * @param   category
     * @method	PUT
     * @return  Category Question  HttpStatus.OK
     */
    @RequestMapping(value = "/testers/updatecategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
    	logger.info("Updating Category " + id);
        
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
     * @param	id
     * @method	DELETE
     * @return 	HttpStatus.NO_CONTENT
     */
    @RequestMapping(value = "/testers/deletecategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") long id) {
    	logger.info("Fetching & Deleting Category with id " + id);
   	 
        Category category = ctgSvc.findOneCategory(id);
        if (category == null) {
            System.out.println("Unable to delete. Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
 
        ctgSvc.deleteOne(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
	
    /**
	 * @param  		id
	 * @method		GET
	 * @return      get a category by id
	 */
    @RequestMapping(value = "/testers/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
    	Category category = ctgSvc.findOneCategory(id);
    	return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
    
    /**
	 * @param  		-
	 * @method		GET
	 * @return      list all question
	 */
//    @RequestMapping(path = "/testers/allquestion", method=RequestMethod.GET)
//	public List<Question> findAllQuestion(){
//    	List<Question> pertanyaanList = quesSvc.findAllQuestion();
//    	
//    	List<Object> jawabanList;
//    	for (int i = 0; i < pertanyaanList.size(); i++) {
//        	Object jawaban = ansSvc.findAnswerByQuestion());
//        	jawabanList.add(jawaban);
//		}
//		return pertanyaanList;
//		return jawabanList;
//	}
    
    /**
	 * @param  		-
	 * @method		GET
	 * @return      list all answer
	 */
    @RequestMapping(path = "/testers/answer", method=RequestMethod.GET)
	public List<Answer> findAllAnswer(){
		return ansSvc.findAllAnswer();
	}
    
    /**
	 * @param  		id
	 * @method		GET
	 * @return      find one answer
	 */
    @RequestMapping(value = "/testers/answer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Answer> getOneAnswerById(@PathVariable("id") int id) {
    	Answer answer = ansSvc.findOne(id);
    	return new ResponseEntity<Answer>(answer, HttpStatus.OK);
    }
    
    /**
     * @param   idQuestion
     * @method  GET
     * @return  question
     */
   @RequestMapping(value = "/testers/question/{id}", method = RequestMethod.GET)
   public ResponseEntity<Question> getQuestionById(@PathVariable("id") int id) {
    Question question = quesSvc.findOneQuestion(id);
    return new ResponseEntity<Question>(question, HttpStatus.OK);
   }
    
	/**
	 * View List of Question by Category Question
	 * @param	id
	 * @method	GET
	 * @return	findAllQuestionByCategory
	 */
	@RequestMapping(path="/question/list/{id}",method=RequestMethod.GET)
	public List<Question> findAllQuestionByCategory(@PathVariable int id){
		logger.info("Fetching Question with id " + id);
		return quesSvc.findAllQuestionByCategory(id);
	}
 
    /**
     * Create Question by id Question and Category Question
     * @param	question
     * @param	ucBuilder
     * @method	POST
     * @return	Question HttpStatus.CREATED
     */
    @RequestMapping(value = "/testers/addquestion", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
    	logger.info("Creating Question " + question.getQuestion());

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
	   * @param  	id
	   * @method	PUT
	   * @return    update a question
	   */
	    @RequestMapping(value = "/testers/updatequestion/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/testers/deletequestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") int id) {
    	logger.info("Fetching & Deleting Question with id " + id);
		 
 
        Question question = quesSvc.findOneQuestion(id);
        if (question == null) {
            System.out.println("Unable to delete. Question with id " + id + " not found");
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }
 
        quesSvc.deleteQuestion(id);
        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }
	
    /**
	 * @param  		id
	 * @method		GET
	 * @return      get a test by user id
	 */
    @RequestMapping(value = "/testers/scores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestUser> getAllScoresByUsers(@PathVariable("id") int id) {
    	logger.info("Fetching Scores with user id " + id);
        return testSvc.findTestByUserId(id);
    }
    
    /**
	 * @param  		id
	 * @method		GET
	 * @return      get user test by id
	 */
    @RequestMapping(value = "/testers/scores/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestUser> getAllScoresByUsers2(@PathVariable("id") int id) {
    	logger.info("Fetching Scores with user id " + id);
        TestUser testUser = testSvc.findUserTestById(id);
        return new ResponseEntity<TestUser>(testUser, HttpStatus.OK);
    }
    
	
	/**
	 * View List of  Detail Result Score User by id user_test
	 * @param 	userId
	 * @method	GET
	 * @return	findTestByUserIdDetail
	 */
	@RequestMapping(path="user/score/detail/{userId}",method=RequestMethod.GET)
	public List<TestUser> findTestByUserId(@PathVariable int userId){
		return testSvc.findTestByUserId(userId);
	}
   
	/**
	 * View List of Assignment User
	 * @method	GET
	 * @return	findAllAssignment
	 */
	 @RequestMapping(path = "/testers/assignment", method=RequestMethod.GET)
		public List<User> findAllUserforAssignment(){
			return userSvc.findAllUser(3);
		}
    
    /**
     * View List Assignment User by id_user
     * @param	id
     * @method	GET
     * @return 	findTestAssignmentById
     */
    @RequestMapping(value = "/testers/assignment/list/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestUser> findTestAssignmentById(@PathVariable("id") int id) {
    	logger.info("Fetching Assignment with user id " + id);
        return testSvc.findTestAssignment(id);
    }
	
	/**
	 * List Category Detail by id
	 * @param 	id
	 * @param	redirectAttributes
	 * @param	model
>>>>>>> 552847bc3281535e39d838f519094e74cfeb0951
	 * @method	GET
	 * @return	list AllTester
	 */
	@RequestMapping(path = "/getalltester", method=RequestMethod.GET)
	public List<User> findAllTester(){
		return userSvc.findAllUser(2);
	}
	
	/**
     * Create User Test by id User and id Categor
     * @param	testUser
     * @param	ucBuilder
     * @method	POST
     * @return	TestUser HttpStatus.CREATED
     */
    @RequestMapping(value = "/testers/assignment/save", method = RequestMethod.POST)
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