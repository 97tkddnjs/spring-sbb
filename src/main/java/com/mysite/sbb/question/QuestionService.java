package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

//    //public List<Question> getList() {
//        return this.questionRepository.findAll();
//    }

    public Question getQuestion(Integer id)
    {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        }else{
            throw  new DataNotFoundException("question not found");
        }
    }

    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.questionRepository.findAll(pageable);
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setContent(content);
        q.setSubject(subject);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
