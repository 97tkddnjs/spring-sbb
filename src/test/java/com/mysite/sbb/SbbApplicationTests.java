package com.mysite.sbb;

import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
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

}
