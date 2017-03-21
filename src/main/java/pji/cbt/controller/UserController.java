package pji.cbt.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	HttpSession session; 
	
	@Autowired
	private TestUserService tstSvc;
	
	@Autowired
	private QuestionService queSvc;
	
	@Autowired
	private AnswerService ansSvc;
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private CategoryService ctgSvc;
	
	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
	public String userPage(HttpServletRequest request, Model Model, Authentication authentication){
		User user = userSvc.findOneUser(authentication.getName());
		session = request.getSession();
		session.setAttribute("idlogin", user.getUserId());
		return "indexuser";
	}
	
	@RequestMapping(path = "/editprofile", method=RequestMethod.GET)
	public String editProfile(Model model) {	
		int idlogin = Integer.parseInt(session.getAttribute("idlogin").toString());
		User user = userSvc.findOne(idlogin);
		model.addAttribute("data", user);
		return "editprofileuser";
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
			return "editprofileuser";
		}
		redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
		return "redirect:/user/dashboard";
	}
	
	@RequestMapping(path ="/editpassword/save", method = RequestMethod.POST)
	public String saveEditPassword(int iduser, String oldpassword, String newpassword, String retypepassword, RedirectAttributes redirectAttributes, Model model) {
		BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
		User user = userSvc.findOne(iduser);
		System.out.println(oldpassword);
		System.out.println(newpassword);
		System.out.println(retypepassword);
		if(!BCrypt.matches(oldpassword, user.getPassword())){
			model.addAttribute("msgpassword", "Fail, wrong old password!!");
			model.addAttribute("data", user);
			return "editprofileuser";
		} else if (!newpassword.equals(retypepassword)) {
			model.addAttribute("msgpassword", "Fail, your new password doesn't match!!");
			model.addAttribute("data", user);
			return "editprofileuser";
		}
		user.setPassword(user.passwordToHash(newpassword));
		userSvc.updatePassword(user);
		redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
		return "redirect:/user/dashboard";
	}
	
	@RequestMapping(path = "/test/list", method=RequestMethod.GET)
	public String Test(HttpServletRequest request, Model model) {		
		List<Category> categories = this.ctgSvc.findAllCategory();
		model.addAttribute("data", categories);
		return "listtest";
	}
	
	@RequestMapping(path = "/test/dotest", method=RequestMethod.POST)
	public String doTest(Category category, Model model, Timestamp timestamp) {
		timestamp = new Timestamp(System.currentTimeMillis());
		List<Question> questions = queSvc.findAllQuestionByCategory(category.getIdCategory());
		String startTest = sdf.format(timestamp);
		List<FormQuestion> formQuestions = new ArrayList<FormQuestion>();
		for(Question question : questions){
			FormQuestion formQuestion = new FormQuestion();
			formQuestion.setQuestion(question);
			List<Answer> answers = ansSvc.findAnswerByQuestion(question.getIdQuestion());			
			formQuestion.setAnswers(answers);
			formQuestion.setCategory(category);
			formQuestions.add(formQuestion);
		}
		model.addAttribute("idCategory", category.getIdCategory());
		model.addAttribute("data", formQuestions);
		model.addAttribute("start", startTest);
		return "formtest";
	}

	@RequestMapping(path = "/test/save", method=RequestMethod.POST)
	public String saveTest(FormAnswer formAnswer, HttpServletRequest request, Model model, Timestamp timestamp) {
		TestUser testUser = new TestUser();
		User user = new User();
		user.setUserId(Integer.parseInt(session.getAttribute("idlogin").toString()));
		timestamp = new Timestamp(System.currentTimeMillis());
		double point = 0;
		double quest = formAnswer.getChoices().size();
		for(String choice : formAnswer.getChoices()){
			if(choice.equalsIgnoreCase("true")){
				point++;
			}
		}
		double score = point/quest*100;
		score = Math.round(score * 100);
		score = score/100;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date dateStart = format.parse(formAnswer.getStartTest());
				testUser.setStarted(dateStart);
				Date dateEnd = format.parse(formAnswer.getStartTest());
				testUser.setEnded(dateEnd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		testUser.setCategories(formAnswer.getCategory());
		testUser.setScore(score);
		testUser.setUsers(user);
		tstSvc.saveTest(testUser);
		return "redirect:/user/test/list";
	}
}
