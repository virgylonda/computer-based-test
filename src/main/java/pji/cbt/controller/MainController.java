package pji.cbt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pji.cbt.entities.User;
import pji.cbt.iservices.AdminService;
import pji.cbt.iservices.SoalService;

@Controller
public class MainController {

	@Autowired
	private AdminService adminSvc;
    
	@Autowired
	private SoalService soalSvc;
	
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String homeLogin(Model model){
    	return "login";
    }
    
    @RequestMapping(value="/login/proccess",method = RequestMethod.POST)
    public String proccessLogin(String username, String password, HttpServletRequest request, Model model){
    	try {	
    		User user = adminSvc.findOneUser(username);
        	String roles = user.getRoles();
        	if(user.getPassword().equalsIgnoreCase(password) && roles.equals("Admin")){	
        		return "redirect: ../../../admin/dashboard";
        	} else {
        		return "redirect:../login";
        	}
    	} catch (Exception e) {
    		return "redirect:../login";
    	}
    }
    
    
    @RequestMapping(value="/user/soal", method= RequestMethod.GET)
    public String soalpage(Model modelsoal){
    	modelsoal.addAttribute("soal",this.soalSvc.findAllSoal());
    	return"/index_soal";
    }
}
