package pji.cbt.controller;

import java.sql.Timestamp;
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

import ch.qos.logback.core.net.SyslogOutputStream;
import pji.cbt.entities.Answer;
import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.TestUser;
import pji.cbt.entities.User;
import pji.cbt.form.FormAnswer;
import pji.cbt.form.FormQuestion;
import pji.cbt.services.AnswerService;
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
	
	public double calculateScore(double point, double quest ){
		double score = point/quest*100;
		score = Math.round(score * 100);
		score = score/100;
		return score;
	}

	@RequestMapping(path = "/dashboard", method = RequestMethod.GET)
	public String userPage(HttpServletRequest request, Model Model, Authentication authentication) {
		User user = userSvc.findOneUser(authentication.getName());
		session = request.getSession();
		session.setAttribute("idlogin", user.getUserId());
		return "indexuser";
	}

	@RequestMapping(path = "/editprofile", method = RequestMethod.GET)
	public String editProfile(Model model) {
		int idlogin = Integer.parseInt(session.getAttribute("idlogin").toString());
		User user = userSvc.findOne(idlogin);
		model.addAttribute("data", user);
		return "editprofileuser";
	}

	@RequestMapping(path = "/editprofile/save", method = RequestMethod.POST)
	public String saveEditProfile(User user, Roles roles, RedirectAttributes redirectAttributes, Model model) {
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

	@RequestMapping(path = "/editpassword/save", method = RequestMethod.POST)
	public String saveEditPassword(int iduser, String oldpassword, String newpassword, String retypepassword,
			RedirectAttributes redirectAttributes, Model model) {
		BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
		User user = userSvc.findOne(iduser);
		if (!BCrypt.matches(oldpassword, user.getPassword())) {
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

	@RequestMapping(path = "/test/list", method = RequestMethod.GET)
	public String Test(HttpServletRequest request, Model model) {
		List<TestUser> testUser = tstSvc
				.findTestHaveAssign(Integer.parseInt(session.getAttribute("idlogin").toString()));
		model.addAttribute("data", testUser);
		return "listtest";
	}

	@RequestMapping(path = "/test/dotest", method = RequestMethod.POST)
	public String doTest(HttpServletRequest request, Category category, FormAnswer formAnswer, TestUser testUser, Model model, int init, Timestamp timestamp) {
		timestamp = new Timestamp(System.currentTimeMillis());
		Question ques = queSvc.findCountQuestion(category.getIdCategory());
		Question question = queSvc.findAllQuestionByCategoryLimit(category.getIdCategory(),1,init);

		if(request.getSession().getAttribute("answer")==null){
			session = request.getSession();
			List<FormAnswer> frmAnswers = new ArrayList<FormAnswer>();
			for (int i = 0; i < ques.getOrderingQuestion(); i++) {
				FormAnswer formAnswerer = new FormAnswer();
				frmAnswers.add(formAnswerer);
			}
			session.setAttribute("answer", frmAnswers);
		} else {
			List<FormAnswer> frmAnswers = (List<FormAnswer>) request.getSession().getAttribute("answer");
			if(formAnswer.getCheckBut().equalsIgnoreCase("next")){
				frmAnswers.set(init-1, formAnswer);
			} else if(formAnswer.getCheckBut().equalsIgnoreCase("back")){
				frmAnswers.set(init+1, formAnswer);
			}	
		}
		
		List<FormAnswer> frmAnswers = (List<FormAnswer>) request.getSession().getAttribute("answer");
		
		String startTest = sdf.format(timestamp);
		try {
			Date date = sdf.parse(startTest);
			testUser.setStarted(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		testUser.setStatus("Done");
		TestUser testUser2 = tstSvc.findUserTestById(testUser.getTestId());
		if(testUser2.getStarted()==null){	
			tstSvc.updateStartTest(testUser);
		}
		FormQuestion formQuestion = new FormQuestion();
		formQuestion.setQuestion(question);
		List<Answer> answers = ansSvc.findAnswerByQuestion(question.getIdQuestion());
		formQuestion.setAnswers(answers);
		formQuestion.setCategory(category);
		
		model.addAttribute("init", init);
		model.addAttribute("category", category.getIdCategory());
		model.addAttribute("idTest", testUser.getTestId());
		model.addAttribute("data", formQuestion);
		model.addAttribute("sessionChoice", frmAnswers.get(init).getChoices());
		model.addAttribute("max", ques.getOrderingQuestion());
		return "formtest";
	}

	@RequestMapping(path = "/test/save", method=RequestMethod.POST)
	public String saveTest(FormAnswer formAnswer, int init, Model model, Timestamp timestamp) {
		timestamp = new Timestamp(System.currentTimeMillis());
		TestUser testUser = new TestUser();
		testUser.setTestId(formAnswer.getTestId());
		String endTest = sdf.format(timestamp);
		try {
			Date date = sdf.parse(endTest);
			testUser.setEnded(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//testUser.setScore(calculateScore(point, quest));
		tstSvc.updateEndTest(testUser);
		return "redirect:/user/test/list";
	}
	
}
