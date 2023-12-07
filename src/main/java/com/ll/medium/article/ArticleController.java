package com.ll.medium.article;

import com.ll.medium.member.Member;
import com.ll.medium.member.MemberRepository;
import com.ll.medium.member.MemberService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.testng.TestNGAntTask;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute(articles);
        return "article_list";
    }
    @GetMapping("/write")
    public String create(ArticleForm articleForm){
        return "article_form";
    }

    @PostMapping("/write")
    public String create(@ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "article_form";
        }

        try{
            principal.getName();
            Member member = memberService.getMemberByUsername(principal.getName());
            articleForm.setWriter(member);
        } catch (NullPointerException e){
            articleForm.setWriter(null);
        }
        articleService.createArticle(articleForm);

        return "redirect:/post/list";
    }

    @GetMapping("/{articleId}")
    public String articleDetail(@PathVariable("articleId") Integer articleId, Model model){
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);

        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{articleId}/modify")
    public String modifyArticle(@PathVariable("articleId") Integer articleId, Principal principal, ArticleForm articleForm){
        Article article = articleService.getArticleById(articleId);

        if(!article.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleForm.setTitle(article.getTitle());
        articleForm.setBody(article.getBody());
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{articleId}/modify")
    public String modifyArticle(@PathVariable("articleId") Integer articleId, Principal principal, ArticleForm articleForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        Article article = articleService.getArticleById(articleId);

        if(!article.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        articleService.modify(article, articleForm);

        return String.format("redirect:/post/%s",articleId);

    }

}
