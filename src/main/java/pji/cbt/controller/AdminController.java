package pji.cbt.controller;

import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pji.cbt.entities.User;
import pji.cbt.iservices.AdminService;

@Controller
@RequestMapping("/admin" )
public class AdminController {
	@Autowired
	private AdminService adminSvc;

	public AdminController() {
	}

	@RequestMapping(path = "/dashboard", method=RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model model) {		
		return "index";
	}

	@RequestMapping(path = "/tester/list", method=RequestMethod.GET)
	public String dataTester(Model model) {
		List<User> user = adminSvc.findAllUser(2);
		model.addAttribute("data", user);
		return "datatester";
	}
	
	@RequestMapping(path = "/tester/delete/{id}", method=RequestMethod.GET)
	public String deleteTester(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			this.adminSvc.deleteOne(id);
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
	public String saveTester(User user, RedirectAttributes redirectAttributes, Model model) {
		String password = user.getPassword();
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			this.adminSvc.createUser(user);
		} catch (Exception ex) {
			user.setPassword(password);
			System.out.println(user.getPassword());
			System.out.println(ex);
			model.addAttribute("msg", "Fail, Username has been used!!");
			model.addAttribute("data", user);
			return "formtester";
		}
		redirectAttributes.addFlashAttribute("msg", "Tester account has been created successfully!!");
		return "redirect:list";
	}
	
	@RequestMapping(path = "/users/list", method=RequestMethod.GET)
	public String dataUsers(Model model) {
		model.addAttribute("data", this.adminSvc.findAllUser(3));
		return "datausers";
	}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.GET)
	public String formAddNewUsers(Model model){
		return "/formusers";
	}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.POST)
	public String saveUsers(User user, RedirectAttributes redirectAttributes, Model model){
		String password = user.getPassword();
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			this.adminSvc.createUser(user);
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
			this.adminSvc.deleteOne(id);
		} catch (Exception e) {
			attributes.addFlashAttribute("msgerror", "Delete Account User Failed! Try Again..");
			return "redirect:../list";	
		}
		attributes.addFlashAttribute("msgsuccess", "Delete Account User Succesfully!");
		return "redirect:../list";
	}
}
