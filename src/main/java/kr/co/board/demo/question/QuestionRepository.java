package kr.co.board.demo.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	@Query("select "
			+ "distinct q from Question q "
			+ "left outer join BbsUser u1 on q.bbsUser = u1 "
			+ "left outer join Answer a on q.bbsUser = a "
			+ "left outer join BbsUser u2 on q.bbsUser = u2 "
			+ "where "
			+ "   q.subject like %:keyword% "
			+ "or q.content like %:keyword% "
			+ "or u1.username like %:keyword% "
			+ "or a.content like %:keyword% "
			+ "or u2.username like %:keyword% "
			)
	Page<Question> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
