package kr.co.board.demo.answer;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import kr.co.board.demo.question.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(columnDefinition = "VARCHAR2(4000)")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question;
}
