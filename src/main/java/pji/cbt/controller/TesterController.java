package pji.cbt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pji.cbt.entities.Question;
import pji.cbt.services.QuestionService;

@Controller
@RequestMapping("/tester")
public class TesterController {
	
	@Autowired
	private QuestionService quesSvc;

	public TesterController(){
	}
	
		@RequestMapping(path="/dashboard", method=RequestMethod.GET)
		public String testerPage(HttpServletRequest request, Model Model) {
			return "indextester";
	}
		
		@RequestMapping(path="/category/list",method=RequestMethod.GET)
		public String dataCategory(Model model){
			model.addAttribute("msg","Tampilan Category");
			return "datacategory";
		}
		
		
		@RequestMapping(path="/listQuestion",method=RequestMethod.GET)
		public String dataQuestion(Model model){
			List<Question> question = this.quesSvc.findAllQuestion();
			model.addAttribute("data",question);
			return "dataquestion";
		}
		
		
		@RequestMapping(path = "/formQuestion", method = RequestMethod.GET)
		public String formAddNewQuestion(Model model) {
			return "formQuestion";
		}
}