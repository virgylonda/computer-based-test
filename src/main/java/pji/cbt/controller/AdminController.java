//package pji.cbt.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import pji.cbt.entities.Answer;
//import pji.cbt.entities.Question;
//import pji.cbt.iservices.AnswerService;
//import pji.cbt.iservices.QuestionService;
//
//@Controller
//@RequestMapping(value="/user")
//public class AdminController {
//	@Autowired
//	private QuestionService quesSvc;
//	
//	@Autowired
//	private AnswerService ansSvc;
//	
//	public AdminController(){	
//	}
//	
//	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
//	public String userHomePage(HttpServletRequest request, Model model ){
//		return "index";
//	}
//	
//	@RequestMapping(path="/dataquestions",method=RequestMethod.GET)
//	public String dataQuestion(Model model){
//		List<Question> question = quesSvc.findAllQuestion();
//		model.addAttribute("question",question);
//		return "index_question";
//	}
//	
//	@RequestMapping(path="/questtion/delete/{question_id}",method=RequestMethod.GET)
//	public String deleteQuestion(@PathVariable int category_id,RedirectAttributes attributes,Model model){
//		try{
//			quesSvc.deleteQuestion();
//		}catch (Exception e) {
//			attributes.addFlashAttribute("msgerror","Delete Question Failed! Try Again");
//			return"redirect:/index_question";
//		}
//			attributes.addFlashAttribute("msgsuccess","Delete Question Success");
//			return "redirect:/index_question";
//		}
//	
//	
//	@RequestMapping(path="/dataanswers", method=RequestMethod.GET)
//	public String dataAnswer(Model model){
//		List<Answer> answer = ansSvc.findAllAnswer();
//		model.addAttribute("answer", answer);
//		return "index_answer";
//	}
//	
//	
//	@RequestMapping(path="/answer/delete/{question_id}",method=RequestMethod.GET)
//	public String deleteAnswer(@PathVariable int question_id,RedirectAttributes attributes,Model model){
//		try{
//			ansSvc.deleteAnswer();
//		}catch (Exception e){
//			attributes.addFlashAttribute("msgerror","Delete Question Failed! Try Again");
//			return "redirect:/index_answer";
//		}
//		attributes.addFlashAttribute("msgsuccess","Delete Question Success");
//			return "redirect:/index_answer";
//	}
//}