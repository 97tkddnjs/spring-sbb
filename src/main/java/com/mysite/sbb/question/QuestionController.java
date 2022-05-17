package com.mysite.sbb.question;

import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor //lombok에서 제공하는 것 자동으로 final 속성 붙은 것 생성자 만듬
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String list(Model model) {
        // 모델 객체는 따로 생성할 필요 없이 컨트롤러 메서드의 매개 변수로 지정하기만 하면
        // 스프링 부트가 자동으로 Model 객체를 생성한다.

        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @RequestMapping(value ="/question/detail/{id}" )
    public String detail(Model model, @PathVariable("id") Integer id)
    {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

}
