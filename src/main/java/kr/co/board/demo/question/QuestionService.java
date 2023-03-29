package kr.co.board.demo.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kr.co.board.demo.answer.Answer;
import kr.co.board.demo.exception.DataNotFoundException;
import kr.co.board.demo.user.BbsUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id){
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(String subject, String content, BbsUser bbsUser) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setBbsUser(bbsUser);
		this.questionRepository.save(q);	
	}
	
	public Page<Question> getList(int page, String keyword){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		Specification<Question> spec = search(keyword);
//		return this.questionRepository.findAll(spec, pageable);
		return this.questionRepository.findAllByKeyword(keyword, pageable);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	public void vote(Question question, BbsUser bbsUser) {
		question.getVoter().add(bbsUser);
		this.questionRepository.save(question);
	}
	
	private Specification<Question> search(String keyword){
		return new Specification<>() {
			private  static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Question, BbsUser> u1 = q.join("bbsUser", JoinType.LEFT);
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
				Join<Answer, BbsUser> u2 = q.join("bbsUser", JoinType.LEFT);
				
				return cb.or( cb.like(q.get("subject"), "%" + keyword + "%")
							, cb.like(q.get("content"), "%" + keyword + "%")
							, cb.like(u1.get("username"), "%" + keyword + "%")
							, cb.like(a.get("content"), "%" + keyword + "%")
							, cb.like(u2.get("username"), "%" + keyword + "%"));
			}
		};
	}
}
