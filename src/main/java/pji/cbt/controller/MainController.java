package pji.cbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pji.cbt.iservices.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userSvc;
	
    @RequestMapping(value="/coba",method = RequestMethod.GET)
    public String homepage(Model model){
    	model.addAttribute("user", this.userSvc.findAll());
        return "/index";
    }
}
