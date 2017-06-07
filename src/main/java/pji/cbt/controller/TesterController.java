package pji.cbt.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@Controller
@RequestMapping("/tester")
public class TesterController {
	
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
	
	public TesterController(){
	}
	
		@RequestMapping(path="/dashboard", method=RequestMethod.GET)
		public String testerPage(HttpServletRequest request, Model Model, Authentication authentication) {
			User user = userSvc.findOneUser(authentication.getName());
			session = request.getSession();
			session.setAttribute("idlogin", user.getUserId());
			return "indextester";
		}
		
		@RequestMapping(path = "/editprofile", method=RequestMethod.GET)
		public String editProfile(Model model) {	
			int idlogin = Integer.parseInt(session.getAttribute("idlogin").toString());
			User user = userSvc.findOne(idlogin);
			model.addAttribute("data", user);
			return "editprofiletester";
		}
		
		@RequestMapping(path ="/editprofile/save", method = RequestMethod.POST)
		public String saveEditProfile(User user, Roles roles ,RedirectAttributes redirectAttributes, Model model) {
			user.setRoles(roles);
			try {
				this.userSvc.updateUser(user);
			} catch (Exception ex) {
				System.out.println(ex);
				model.addAttribute("msg", "Fail, to update tester profile!!");
				model.addAttribute("data", user);
				return "editprofiletester";
			}
			redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
			return "redirect:/tester/dashboard";
		}
		
		@RequestMapping(path ="/editpassword/save", method = RequestMethod.POST)
		public String saveEditPassword(int iduser, String oldpassword, String newpassword, String retypepassword, RedirectAttributes redirectAttributes, Model model) {
			BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
			User user = userSvc.findOne(iduser);
			if(!BCrypt.matches(oldpassword, user.getPassword())){
				model.addAttribute("msgpassword", "Fail, wrong old password!!");
				model.addAttribute("data", user);
				return "editprofiletester";
			} else if (!newpassword.equals(retypepassword)) {
				model.addAttribute("msgpassword", "Fail, your new password doesn't match!!");
				model.addAttribute("data", user);
				return "editprofiletester";
			}
			user.setPassword(user.passwordToHash(newpassword));
			userSvc.updatePassword(user);
			redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
			return "redirect:/tester/dashboard";
		}
		
		@RequestMapping(path="/category/list",method=RequestMethod.GET)
		public String dataCategory(Model model){
			List<Category>category = this.ctgSvc.findAllCategory();
			model.addAttribute("data", category);
			return "datacategory";
		}
		
		@RequestMapping(path="/createnewcategory", method= RequestMethod.GET)
		public String formAddNewUsers(Model model){
			return "/formcategory";
		}
		
		@RequestMapping(path="/createnewcategory", method= RequestMethod.POST)
		public String createCategory(Category category, RedirectAttributes redirectAttributes, Model model){
			try {
				this.ctgSvc.createCategory(category);
			} catch (Exception e) {
				System.out.println(e);
				model.addAttribute("msg", "Fail, Category name has been used!!");
				model.addAttribute("data", category);
				return "formcategory";
			}	
		redirectAttributes.addFlashAttribute("msg", "Create new category has been successfully!!");
		return "redirect:../tester/category/list";
		}
		
		@RequestMapping(path = "/category/delete/{id}", method=RequestMethod.GET)
		public String deleteCategory(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
			try {
				this.ctgSvc.deleteOne(id);
			} catch (Exception ex) {
				redirectAttributes.addFlashAttribute("msgerror", "Fail to delete category!!");
				return "redirect:../list";
			}
			redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete category!!");
			return "redirect:../list";
		}
		
		@RequestMapping(path = "/question/delete/{category}/{id}", method=RequestMethod.GET)
		public String deleteQuestion(@PathVariable int id, @PathVariable int category, RedirectAttributes redirectAttributes, Model model) {
			Question question = quesSvc.findOneQuestion(id);
			Category categories = new Category();
			categories.setIdCategory(category);
			question.setCategory(categories);
			try {
				this.quesSvc.deleteQuestion(id);
				this.quesSvc.updateOrderingQuestion(question.getCategory().getIdCategory(), question.getOrderingQuestion());
			} catch (Exception ex) {
				redirectAttributes.addFlashAttribute("msgerror", "Fail to delete question!!");
				return "redirect:/tester/question/list/{category}";
			}
			redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete question!!");
			return "redirect:/tester/question/list/{category}";
		}
		
		@RequestMapping(path = "/category/edit/{id}", method=RequestMethod.GET)
		public String findOneCategory(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
			Category category = this.ctgSvc.findOneCategory(id);
			model.addAttribute("data", category);
			return "formeditcategory";
		}
		
		@RequestMapping(path ="/category/edit/editcategory", method = RequestMethod.POST)
		public String updateCategory(Category category, RedirectAttributes redirectAttributes, Model model) {
			try {
				this.ctgSvc.updateCategory(category);
			} catch (Exception ex) {
				System.out.println(ex);
				model.addAttribute("msg", "Fail, to update question category!!");
				model.addAttribute("data", category);
				return "formeditcategory";
			}
			redirectAttributes.addFlashAttribute("msg", "Question category has been update successfully!!");
			return "redirect:../list";
		}
		
		@RequestMapping(path="/question/list/{id}",method=RequestMethod.GET)
		public String dataQuestion(@PathVariable int id, Model model){
			List<Question> questions = this.quesSvc.findAllQuestionByCategory(id);
			model.addAttribute("data", questions);
			model.addAttribute("id", id);
			return "dataquestion";
		}
		
		
		@RequestMapping(path = "/createnewquestion/{id}/{questionType}", method = RequestMethod.GET)
		public String formAddNewQuestion(@PathVariable int id, @PathVariable String questionType, Model model) {
			model.addAttribute("idcategory", id);
			if(questionType.equalsIgnoreCase("essay")){
				return "formQuestionEssay";
			}
			else{
				return "formQuestion";	
			}
			
		}
		
		@RequestMapping(path = "/question/edit/{id}", method = RequestMethod.GET)
		public String formEditQuestion(@PathVariable int id, Model model) {
			Question question = quesSvc.findOneQuestion(id);
			List<Answer> answers = ansSvc.findAnswerByQuestion(id);
			FormQuestion formQuestion = new FormQuestion();
			formQuestion.setQuestion(question);
			formQuestion.setAnswers(answers);
			model.addAttribute("dataedit", formQuestion);
			return "formeditquestion";
		}
		
		@RequestMapping(path = "/question/edit/save", method = RequestMethod.POST)
		public String EditSaveQuestion(FormQuestion formQuestion, Model model) {	
			int choice = Integer.valueOf(formQuestion.getChoice());
			quesSvc.editQuestion(formQuestion.getQuestion());
			int i = 0;
			for(Answer answer : formQuestion.getAnswers()){
				if(choice==i){
					answer.setCorrectAnswer(true);
				} else {
					answer.setCorrectAnswer(false);
				}
				ansSvc.editAnswer(answer);
				i++;
			}
			return "redirect:/tester/dashboard";
		}
		
		@RequestMapping(path = "/createnewquestion/save", method = RequestMethod.POST)
		public String saveNewQuestion(FormQuestion formQuestion, Model model) {
			int choice = Integer.valueOf(formQuestion.getChoice());
			Question question = formQuestion.getQuestion();
			question.setCategory(formQuestion.getCategory());
			int i = 0;
			try {
				quesSvc.createQuestion(question);
				for(Answer answer : formQuestion.getAnswers()){
					if(choice==i){
						answer.setCorrectAnswer(true);
					} else {
						answer.setCorrectAnswer(false);
					}
					answer.setQuestionAnswer(question);
					ansSvc.createAnswer(answer);
					i++;
				}
			} catch (Exception ex) {
				System.out.println(ex);
				return "formQuestion";
			}
			return "redirect:/tester/question/list/"+formQuestion.getCategory().getIdCategory();
		}
		
		@RequestMapping(path="user/score/detail/{userId}",method=RequestMethod.GET)
		public String dataScoreTest(@PathVariable Integer userId, Model model){
			List<TestUser> scores = this.testSvc.findTestByUserId(userId);
			model.addAttribute("scores",scores);
			return "index_score";
		}
		
		@RequestMapping(path="/user/score",method=RequestMethod.GET)
		public String dataUserTest(Model model){
			List<TestUser> userTest = this.testSvc.findUserSummaryScore();
			model.addAttribute("userTest",userTest);
			return "index_usertest";
		}
		
		@RequestMapping(path="/user/assignment",method=RequestMethod.GET)
		public String dataUserAssignment(Model model){
			model.addAttribute("data", this.userSvc.findAllUser(3));
			return "testassignment";
		}
		
		@RequestMapping(path="/user/assignment/list/{id}",method=RequestMethod.GET)
		public String dataUserAssignmentList(@PathVariable int id, Model model){
			List <TestUser> tests = testSvc.findTestAssignment(id);
			model.addAttribute("dataid", id);
			model.addAttribute("data", tests);
			return "listassignment";
		}
		
		@RequestMapping(path="/user/assignment/save",method=RequestMethod.POST)
		public String dataUserAssignmentSave(FormAssignment formAssignment,RedirectAttributes redirectAttributes, Model model){
			try{
				testSvc.deleteByIdUserAndStatus(formAssignment.getUser().getUserId());
				for(int idCategory : formAssignment.explodeString(formAssignment.getCategories())){
					TestUser testUser = new TestUser();
					Category category = new Category();
					category.setIdCategory(idCategory);
					testUser.setCategories(category);
					testUser.setUsers(formAssignment.getUser());
					testSvc.saveTest(testUser);
				}
			} catch (Exception ex) {
				redirectAttributes.addFlashAttribute("msgerror","Fail asign test to user!!");
				return "redirect:/tester/user/assignment";
			}
			redirectAttributes.addFlashAttribute("msgsuccess","Success asign test to user!!");
			return "redirect:/tester/user/assignment";
		}
} 