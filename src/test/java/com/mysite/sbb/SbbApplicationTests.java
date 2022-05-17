package com.mysite.sbb;

import com.mysite.sbb.entity.Answer;
import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.AnswerRepository;
import com.mysite.sbb.repository.QuestionRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@Transactional
	void qTestJpa() {
		Question q1 = new Question();

		q1.setSubject("sbb가 뭐고?");
		q1.setContent("sbb 알려줘 ㅈㄱㄴ");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();

		q2.setSubject("spring boot가 뭐고?");
		q2.setContent("id 자동 생성?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

	}
	@Test
	void qTestJpa2() {
		List<Question> all = this.questionRepository.findAll();
		Assertions.assertEquals(4, all.size());

		Optional<Question> oq = this.questionRepository.findById((1));
		if(oq.isPresent()){ //null이 아닌지 확인 후
			Question q = oq.get(); // get을 통해 값을 가져와야 한다
		}
	}

	@Test
	void qTestUpdateDelete(){
		Optional<Question> oq = this.questionRepository.findById(1);
		Assertions.assertTrue(oq.isPresent());
		Question q = oq.get();
		//변경
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);

		//삭제
		this.questionRepository.delete(q);
		Assertions.assertEquals(3, this.questionRepository.count());
	}

	@Test
	void ansTestJpa() {
		Optional<Question> oq =this.questionRepository.findById(2);
		Assertions.assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	void ansSearch() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		Assertions.assertTrue(oa.isPresent());

		Answer a = oa.get();
		Assertions.assertEquals(2, a.getQuestion().getId());
	}


}
