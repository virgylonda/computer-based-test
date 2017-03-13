package pji.cbt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tester")
public class TesterController {

	public TesterController(){
	}
	
		@RequestMapping(path="/dashboard", method=RequestMethod.GET)
		public String testerPage(HttpServletRequest request, Model Model) {
			return "indextester";
	}
}