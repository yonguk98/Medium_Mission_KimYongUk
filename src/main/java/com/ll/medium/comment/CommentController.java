package com.ll.medium.comment;

import com.ll.medium.article.Article;
import com.ll.medium.article.ArticleService;
import com.ll.medium.member.Member;
import com.ll.medium.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{articleId}/comment")
public class CommentController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final CommentService commentService;


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String writeComment(@PathVariable("articleId") Integer articleId,
                               @ModelAttribute CommentForm commentForm,
                               Model model,
                               Principal principal,
                               BindingResult bindingResult){

        Article article = articleService.getArticleById(articleId);
        Member member = memberService.getMemberByUsername(principal.getName());

        if(bindingResult.hasErrors()){
            model.addAttribute("article", article);
            return "article_detail";
        }

        commentService.createComment(commentForm,article,member);
        return "redirect:/post/"+articleId;
    }

}
