package pji.cbt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;

@Controller
@RequestMapping("/tester")
public class TesterController {
	
	@Autowired
	private QuestionService quesSvc;

	@Autowired
	private CategoryService ctgSvc;
	
	public TesterController(){
	}
	
		@RequestMapping(path="/dashboard", method=RequestMethod.GET)
		public String testerPage(HttpServletRequest request, Model Model) {
			return "indextester";
		}
		
		@RequestMapping(path="/category/list",method=RequestMethod.GET)
		public String dataCategory(Model model){
			List<Category>category = this.ctgSvc.findAllCategory();
			model.addAttribute("data", category);
			return "datacategory";
		}
		
		@RequestMapping(path="/createnewcategory", method= RequestMethod.GET)
		public String formAddNewUsers(Model model){
			return "/formcategory";
		}
		
		@RequestMapping(path="/createnewcategory", method= RequestMethod.POST)
		public String createCategory(Category category, RedirectAttributes redirectAttributes, Model model){
			try {
				this.ctgSvc.createCategory(category);
			} catch (Exception e) {
				System.out.println(e);
				model.addAttribute("msg", "Fail, Category name has been used!!");
				model.addAttribute("data", category);
				return "formcategory";
			}	
		redirectAttributes.addFlashAttribute("msg", "Create new category has been successfully!!");
		return "redirect:../tester/category/list";
		}
		
		@RequestMapping(path = "/category/delete/{id}", method=RequestMethod.GET)
		public String deleteCategory(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
			try {
				this.ctgSvc.deleteOne(id);
			} catch (Exception ex) {
				redirectAttributes.addFlashAttribute("msgerror", "Fail to delete category!!");
				return "redirect:../list";
			}
			redirectAttributes.addFlashAttribute("msgsuccess", "Success to delete category!!");
			return "redirect:../list";
		}
		
		@RequestMapping(path = "/category/edit/{id}", method=RequestMethod.GET)
		public String findOneCategory(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
			Category category = this.ctgSvc.findOneCategory(id);
			model.addAttribute("data", category);
			return "formeditcategory";
		}
		
		@RequestMapping(path ="/category/edit/editcategory", method = RequestMethod.POST)
		public String updateCategory(Category category, RedirectAttributes redirectAttributes, Model model) {
			try {
				this.ctgSvc.updateCategory(category);
			} catch (Exception ex) {
				System.out.println(ex);
				model.addAttribute("msg", "Fail, to update question category!!");
				model.addAttribute("data", category);
				return "formeditcategory";
			}
			redirectAttributes.addFlashAttribute("msg", "Question category has been update successfully!!");
			return "redirect:../list";
		}
		
		@RequestMapping(path="/question/list/{id}",method=RequestMethod.GET)
		public String dataQuestion(Model model){
			List<Question> question = this.quesSvc.findAllQuestion();
			model.addAttribute("data", question);
			return "dataquestion";
		}
		
		
		@RequestMapping(path = "/createnewquestion/{id}", method = RequestMethod.GET)
		public String formAddNewQuestion(Model model) {
			return "formQuestion";
		}
}