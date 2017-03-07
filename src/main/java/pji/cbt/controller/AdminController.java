package pji.cbt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		model.addObject("admin", adminSvc.findAllAdmin());
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
}
