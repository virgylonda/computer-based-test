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

	@RequestMapping(path = "/tester/data", method=RequestMethod.GET)
	public String dataTester(Model model) {
		List<User> user = adminSvc.findAllUser("Tester");
		model.addAttribute("data", user);
		return "datatester";
	}
	
	@RequestMapping(path = "/tester/delete/{id}", method=RequestMethod.GET)
	public String deleteTester(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			adminSvc.deleteOne(id);
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("msgerror", "Fail to delete account!!");
			return "redirect:../data";
		}
		redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete account!!");
		return "redirect:../data";
	}

	@RequestMapping(path ="/tester/form", method = RequestMethod.GET)
	public String formTester(Model model) {
		return "formtester";
	}

	@RequestMapping(path ="/tester/form", method = RequestMethod.POST)
	public String saveTester(User user, RedirectAttributes redirectAttributes, Model model) {
		String password = user.getPassword();
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			adminSvc.createUser(user);
		} catch (Exception ex) {
			user.setPassword(password);
			System.out.println(user.getPassword());
			System.out.println(ex);
			model.addAttribute("msg", "Fail, Username has been used!!");
			model.addAttribute("data", user);
			return "formtester";
		}
		redirectAttributes.addFlashAttribute("msg", "Tester account has been created successfully!!");
		return "redirect:data";
	}
	
	@RequestMapping(path = "/users/datausers", method=RequestMethod.GET)
	public String dataUsers(Model model) {
		List<User> user = adminSvc.findAllUser("User");
		model.addAttribute("data", user);
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
			String pass = user.getPassword();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest);
			user.setPassword(myHash);
			adminSvc.createUser(user);
		} catch (Exception e) {
			System.out.println(e);
			user.setPassword(password);
	
			model.addAttribute("msg", "Fail, Username has been used!!");
			model.addAttribute("data", user);
			return "formusers";
		}	
	redirectAttributes.addFlashAttribute("msg", "Create account User has been successfully!!");
	return "redirect:datausers";
	}
	
	@RequestMapping(path="/users/delete/{user_id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable long user_id, RedirectAttributes attributes, Model model){
		try{
			adminSvc.deleteOne(user_id);
		} catch (Exception e) {
			attributes.addFlashAttribute("msgerror", "Delete Account User Failed! Try Again..");
			return "redirect:../datausers";	
		}
		attributes.addFlashAttribute("msgsuccess", "Delete Account User Succesfully!");
		return "redirect:../datausers";
	}
}
