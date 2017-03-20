package pji.cbt.controller;

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

import pji.cbt.entities.Category;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.services.CategoryService;
import pji.cbt.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	HttpSession session; 
	
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
		List<Category>category = this.ctgSvc.findAllCategory();
		model.addAttribute("data", category);
		return "listtest";
	}

}
