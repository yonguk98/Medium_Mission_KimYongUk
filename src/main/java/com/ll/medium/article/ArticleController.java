package com.ll.medium.article;

import com.ll.medium.member.Member;
import com.ll.medium.member.MemberRepository;
import com.ll.medium.member.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute(articles);
        return "article_list";
    }
    @GetMapping("/create")
    public String create(ArticleForm articleForm){
        return "article_form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "article_form";
        }

        Member member = memberService.getMemberByUsername(principal.getName());
        articleForm.setWriter(member);
        articleService.createArticle(articleForm);

        return "redirect:/article/list";
    }
}
