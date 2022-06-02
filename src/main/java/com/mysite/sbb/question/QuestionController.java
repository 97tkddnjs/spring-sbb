package com.mysite.sbb.question;

import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor //lombok에서 제공하는 것 자동으로 final 속성 붙은 것 생성자 만듬
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        // 모델 객체는 따로 생성할 필요 없이 컨트롤러 메서드의 매개 변수로 지정하기만 하면
        // 스프링 부트가 자동으로 Model 객체를 생성한다.

        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging",paging);
        return "question_list";
    }

    @RequestMapping(value ="/question/detail/{id}" )
    public String detail(Model model, @PathVariable("id") Integer id)
    {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/question/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/question/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject() ,questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}
