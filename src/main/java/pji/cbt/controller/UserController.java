package pji.cbt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public UserController(){
		
	}
	
	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
	public String userPage(HttpServletRequest request, Model Model){
		return "indexuser";
	}

}
