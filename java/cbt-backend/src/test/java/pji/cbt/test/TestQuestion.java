package pji.cbt.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pji.cbt.entities.Category;
import pji.cbt.entities.Question;
import pji.cbt.services.CategoryService;
import pji.cbt.services.QuestionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQuestion {

	@Autowired
	private QuestionService quesSvc;
	
	@Autowired
	private CategoryService categorySvc;

	@Test
	public void findQuestionRandomOrder_Return_QuestionRandom() {
		// Arrange
		Category c1 = categorySvc.findOneCategory(1);
		Category c2 = categorySvc.findOneCategory(2);
		
		Question q1 = new Question(777, 1, "apakah a?", c2);
		Question q2 = new Question(778, 1, "apakah b?", c2);
		Question q3 = new Question(779, 1, "apakah c?", c2);
		Question q4 = new Question(780, 1, "apakah d?", c2);
		Question q5 = new Question(781, 2, "1-1?...", c1);
		
		quesSvc.createQuestion(q1);
		quesSvc.createQuestion(q2);
		quesSvc.createQuestion(q3);
		quesSvc.createQuestion(q4);
		quesSvc.createQuestion(q5);

		// Act
		List<Question> listQ = quesSvc.findQuestionRandomOrder(2);
		List<Question> listQ2 = quesSvc.findQuestionRandomOrder(2);
		List<Question> listQ3 = quesSvc.findQuestionRandomOrder(2);

		// Assert
		assertNotEquals(listQ, listQ2);
		assertNotEquals(listQ2, listQ3);
		for(Question qq : listQ){
			System.out.println(qq.getQuestion());
		}
		System.out.println("==========================");
		for(Question qq : listQ2){
			System.out.println(qq.getQuestion());
		}
		System.out.println("==========================");
		for(Question qq : listQ3){
			System.out.println(qq.getQuestion());
		}
		System.out.println("==========================");
		
		// After
		quesSvc.deleteQuestion(q1.getIdQuestion());
		quesSvc.deleteQuestion(q2.getIdQuestion());
		quesSvc.deleteQuestion(q3.getIdQuestion());
		quesSvc.deleteQuestion(q4.getIdQuestion());
		quesSvc.deleteQuestion(q5.getIdQuestion());

	}
}
