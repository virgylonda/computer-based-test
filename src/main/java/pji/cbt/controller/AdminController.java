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
	public ModelAndView homePage(HttpServletRequest request) {		
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@RequestMapping(path = "/tester/data", method=RequestMethod.GET)
	public ModelAndView dataTester() {
		ModelAndView model = new ModelAndView("datatester");
		List<User> user = adminSvc.findAllUser("Tester");
		model.addObject("data", user);
		return model;
	}
	
	@RequestMapping(path = "/tester/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTester(@PathVariable long id, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		try {
			adminSvc.deleteOne(id);
		} catch (Exception ex) {
			model.setViewName("redirect:../data");
			redirectAttributes.addFlashAttribute("msgerror", "Fail to delete account!!");
		}
		
		model.setViewName("redirect:../data");
		redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete account!!");
		return model;
	}

	@RequestMapping(path ="/tester/form", method = RequestMethod.GET)
	public ModelAndView formTester() {
		ModelAndView model = new ModelAndView();
		model.setViewName("formtester");
		return model;
	}

	@RequestMapping(path ="/tester/form", method = RequestMethod.POST)
	public ModelAndView saveTester(User user, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		try {
			user.setPassword(user.passwordToHash(user.getPassword()));
			adminSvc.createUser(user);
		} catch (Exception ex) {
			System.out.println(ex);
			ModelAndView modelFailed = new ModelAndView("formtester");
			modelFailed.addObject("msg", "Fail, Username has been used!!");
			modelFailed.addObject("data", user);
			return modelFailed;
		}
		model.setViewName("redirect:data");
		redirectAttributes.addFlashAttribute("msg", "Tester account has been created successfully!!");
		return model;
	}
	
	@RequestMapping(path = "/users/datausers", method=RequestMethod.GET)
	public ModelAndView dataUsers() {
		ModelAndView model = new ModelAndView("datausers");
		List<User> user = adminSvc.findAllUser("User");
		model.addObject("data", user);
		return model;
	}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.GET)
	public String formAddNewUsers(Model model2){
		return "/formusers";
	}
	
	@RequestMapping(path="/users/createnew", method= RequestMethod.POST)
	public ModelAndView saveUsers(User user, RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView();
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
			ModelAndView modelGagal = new ModelAndView("formusers");
			modelGagal.addObject("msg", "Fail, Username has been used!!");
			modelGagal.addObject("data", user);
			return modelGagal;
		}
		
	modelAndView.setViewName("redirect:datausers");
	redirectAttributes.addFlashAttribute("msg", "Create account User has been successfully!!");
	return modelAndView;
	}
	
	@RequestMapping(path="/users/delete/{user_id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable long user_id, RedirectAttributes attributes){
		try{
			adminSvc.deleteOne(user_id);
		} catch (Exception e) {
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:../datausers");
			attributes.addFlashAttribute("msgerror", "Delete Account User Failed! Try Again..");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:../datausers");
		attributes.addFlashAttribute("msgsuccess", "Delete Account User Succesfully!");
		return model;
	}
}
