package pji.cbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pji.cbt.iservices.AdminService;

@Controller
public class MainController {

	@Autowired
	private AdminService adminSvc;
    
	
    @RequestMapping(value="/login")
    public String homeLogin(){
    	return "login";
    }
    
    
}
