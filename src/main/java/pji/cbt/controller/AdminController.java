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
import pji.cbt.entities.Question;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.services.AnswerService;
import pji.cbt.services.QuestionService;
import pji.cbt.services.UserService;

@Controller
@RequestMapping("/admin" )
public class AdminController {
	
	@Autowired
	HttpSession session; 
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private QuestionService quesSvc;
	
	@Autowired
	private AnswerService ansSvc;

	public AdminController() {
	}

	@RequestMapping(path = "/dashboard", method=RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model model, Authentication authentication) {		
		User user = userSvc.findOneUser(authentication.getName());
		session = request.getSession();
		session.setAttribute("idlogin", user.getUserId());
		return "index";
	}

	@RequestMapping(path = "/editprofile", method=RequestMethod.GET)
	public String editProfile(Model model) {	
		int idlogin = Integer.parseInt(session.getAttribute("idlogin").toString());
		User user = userSvc.findOne(idlogin);
		model.addAttribute("data", user);
		return "editprofileadmin";
	}
	//
	@RequestMapping(path = "/tester/list", method=RequestMethod.GET)
	public String dataTester(Model model) {
		List<User> users = this.userSvc.findAllUser(2);
		model.addAttribute("data", users);
		return "datatester";
	}
	
	@RequestMapping(path = "/tester/edit/{id}", method=RequestMethod.GET)
	public String formEditTester(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		User user = userSvc.findOne(id);
		List<Roles> roles = userSvc.findRoleAll();
		model.addAttribute("data", user);
		model.addAttribute("datarole", roles);
		return "formedittester";
	}
	
	
	@RequestMapping(path ="/tester/edit/save", method = RequestMethod.POST)
	public String saveEditTester(User user, Roles roles ,RedirectAttributes redirectAttributes, Model model) {
		user.setRoles(roles);
		try {
			this.userSvc.updateUser(user);
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("msg", "Fail, to update tester profile!!");
			model.addAttribute("data", user);
			return "formedittester";
		}
		redirectAttributes.addFlashAttribute("msg", "Tester account has been update successfully!!");
		return "redirect:../list";
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
			return "editprofileadmin";
		}
		redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
		return "redirect:/admin/dashboard";
	}
	
	@RequestMapping(path ="/editpassword/save", method = RequestMethod.POST)
	public String saveEditPassword(int iduser, String oldpassword, String newpassword, String retypepassword, RedirectAttributes redirectAttributes, Model model) {
		BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
		User user = userSvc.findOne(iduser);
		if(!BCrypt.matches(oldpassword, user.getPassword())){
			model.addAttribute("msgpassword", "Fail, wrong old password!!");
			model.addAttribute("data", user);
			return "editprofileadmin";
		} else if (!newpassword.equals(retypepassword)) {
			model.addAttribute("msgpassword", "Fail, your new password doesn't match!!");
			model.addAttribute("data", user);
			return "editprofileadmin";
		}
		user.setPassword(user.passwordToHash(newpassword));
		userSvc.updatePassword(user);
		redirectAttributes.addFlashAttribute("msg", "Your account has been update successfully!!");
		return "redirect:/admin/dashboard";
	}
	
	@RequestMapping(path = "/users/edit/{id}", method=RequestMethod.GET)
	public String formEditUser(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		User user = userSvc.findOne(id);
		List<Roles> roles = userSvc.findRoleAll();
		model.addAttribute("data", user);
		model.addAttribute("datarole", roles);
		return "formedituser";
	}
	
	@RequestMapping(path ="/users/edit/save", method = RequestMethod.POST)
	public String saveEditUser(User user, Roles roles ,RedirectAttributes redirectAttributes, Model model) {
		user.setRoles(roles);
		try {
			this.userSvc.updateUser(user);
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("msg", "Fail, to update user profile!!");
			model.addAttribute("data", user);
			return "formedituser";
		}
		redirectAttributes.addFlashAttribute("msg", "User account has been update successfully!!");
		return "redirect:../list";
	}
	
	@RequestMapping(path = "/tester/delete/{id}", method=RequestMethod.GET)
	public String deleteTester(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			this.userSvc.deleteOne(id);
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror", "Fail to delete account!!");
			return "redirect:../list";
		}
		redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete account!!");
		return "redirect:../list";
	}

	@RequestMapping(path ="/tester/createnew", method = RequestMethod.GET)
	public String formTester(Model model) {
		return "formtester";
	}

	@RequestMapping(path ="/tester/createnew", method = RequestMethod.POST)
	public String saveTester(User user, Roles role,RedirectAttributes redirectAttributes, Model model) {
		String password = user.getPassword();
		user.setRoles(role);
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			this.userSvc.createUser(user);
		} catch (Exception ex) {
			user.setPassword(password);
			System.out.println(ex);
			model.addAttribute("msg", "Fail, Username has been used!!");
			model.addAttribute("data", user);
			return "formtester";
		}
		redirectAttributes.addFlashAttribute("msg", "Tester account has been created successfully!!");
		return "redirect:list";
	}
	

	//ini 
			@RequestMapping(path="/user/list",method=RequestMethod.GET)
			public String testUser(Model model){
				List<User> user = this.userSvc.findAllUsers();
				model.addAttribute("data", user);
				return "datausers";
			}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.GET)
	public String formAddNewUsers(Model model){
		return "/formusers";
	}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.POST)
	public String saveUsers(User user, Roles role ,RedirectAttributes redirectAttributes, Model model){
		String password = user.getPassword();
		user.setRoles(role);
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			this.userSvc.createUser(user);
		} catch (Exception e) {
			System.out.println(e);
			user.setPassword(password);
	
			model.addAttribute("msg", "Fail, Username has been used!!");
			model.addAttribute("data", user);
			return "formusers";
		}	
	redirectAttributes.addFlashAttribute("msg", "Create account User has been successfully!!");
	return "redirect:list";
	}
	
	@RequestMapping(path="/users/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable long id, RedirectAttributes attributes, Model model){
		try{
			this.userSvc.deleteOne(id);
		} catch (Exception e) {
			attributes.addFlashAttribute("msgerror", "Delete Account User Failed! Try Again..");
			return "redirect:../list";	
		}
		attributes.addFlashAttribute("msgsuccess", "Delete Account User Succesfully!");
		return "redirect:../list";
	}
	
	@RequestMapping(path="/tester", method=RequestMethod.GET)
	public String userHomePage(HttpServletRequest request, Model model ){
		return "index";
	}
	
	@RequestMapping(path="/tester/listquestions",method=RequestMethod.GET)
	public String dataQuestion(Model model){
		List<Question> question = this.quesSvc.findAllQuestion();
		model.addAttribute("question",question);
		return "index_question";
	}
	
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
	
	
	@RequestMapping(path="/tester/listanswers", method=RequestMethod.GET)
	public String dataAnswer(Model model){
		List<Answer> answer = this.ansSvc.findAllAnswer();
		model.addAttribute("answer", answer);
		return "index_answer";
	}
	
	
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
	
	@RequestMapping(path="/answer/delete/{id}",method=RequestMethod.GET)
	public String findOneAnswer(int questionId ,Model model){
		model.addAttribute("answer", this.ansSvc.findOne(questionId));
		return "";
		
	}
	
}
//punya saya