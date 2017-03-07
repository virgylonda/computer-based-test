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
    public ModelAndView homeLogin(){
    	ModelAndView model = new ModelAndView("login");
    	return model;
    }
    
    @RequestMapping(value="/login/proccess",method = RequestMethod.POST)
    public ModelAndView proccessLogin(String username, String password, HttpServletRequest request){
    	ModelAndView model = new ModelAndView();
    	try {	
    		User user = adminSvc.findOneUser(username);
        	String roles = user.getRoles();
        	if(user.getPassword().equalsIgnoreCase(password) && roles.equals("Admin")){	
        		model.setViewName("redirect: ../../../admin/dashboard");
        		return model;
        	} else {
        		model.setViewName("redirect:../login");
        		return model;
        	}
    	} catch (Exception e) {
    		model.setViewName("redirect:../login");
    		return model;
    	}
    }
    
    
    @RequestMapping(value="/soal", method= RequestMethod.GET)
    public String soalpage(Model modelsoal){
    	modelsoal.addAttribute("soal",this.soalSvc.findAllSoal());
    	return"/index_soal";
    }
}
