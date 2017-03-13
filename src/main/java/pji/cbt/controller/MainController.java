package pji.cbt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pji.cbt.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userSvc;
	
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String homeLogin(Model model){
    	return "login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
//    @RequestMapping(value="/login/proccess",method = RequestMethod.POST)
//    public String proccessLogin(String username, String password, HttpServletRequest request, Model model){
//    	try {	
//    		User user = userSvc.findOneUser(username);
////        	String roles = user.getRoles();
//        	if(user.getPassword().equalsIgnoreCase(password) && roles.equals("Admin")){	
//        		return "redirect: ../../../admin/dashboard";
//        	} else {
//        		return "redirect:../login";
//        	}
//    	} catch (Exception e) {
//    		return "redirect:../login";
//    	}
//    }
    
    
  
}
