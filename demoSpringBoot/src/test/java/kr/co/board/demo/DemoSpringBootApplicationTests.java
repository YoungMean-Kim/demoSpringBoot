package kr.co.board.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.demo.answer.Answer;
import kr.co.board.demo.answer.AnswerRepository;
import kr.co.board.demo.question.Question;
import kr.co.board.demo.question.QuestionRepository;
import kr.co.board.demo.question.QuestionService;

@SpringBootTest
class DemoSpringBootApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
//	@Test
//	void contextLoads() {
//	}

//	@Test
//	void testJpa() {        
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);  // 첫번째 질문 저장
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);  // 두번째 질문 저장
//    }
	
//	@Test
//	void testJpa() {
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
//	}

//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(103);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}
//	}
	
//	@Test
//	void testJpa() {
//		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(102, q.getId());
//	}

//	@Test
//	void testJpa() {
//		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//		assertEquals(102, q.getId());
//	}

//	@Test
//	void testJpa() {
//		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//		Question q = qList.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
//	}

//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(102);
//		assertTrue(true);
//		
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
//	}
	
//	@Test
//	void testJpa() {
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(102);
//		assertTrue(true);
//		
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, this.questionRepository.count());
//	}

//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(103);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//				
//		Answer a = new Answer();
//		a.setContent("네 자동으로 생성됩니다.");
//		a.setQuestion(q);
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);
//	}
	
//	@Test
//	void testJpa() {
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		
//		Answer a = oa.get();
//		assertEquals(103, a.getQuestion().getId());
//	}

	@Transactional
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(103);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		System.out.println("TEST[aaaaaaa]: " + answerList.size());
		System.out.println("TEST[bbbbbbb]: " + answerList.get(0).getContent());
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}


//	@Autowired
//	private QuestionService questionService;
//
//	@Test
//	void testJpa() {
//		for (int i = 1; i <= 300; i++) {
//            String subject = String.format("테스트 데이터입니다:[%03d]", i);
//            String content = "테스트 데이타 내용 입력 입니다.";
//            this.questionService.create(subject, content);
//        }
//
//	}
	
}
