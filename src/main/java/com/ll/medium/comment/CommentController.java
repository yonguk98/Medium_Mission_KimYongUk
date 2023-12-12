package com.ll.medium.comment;

import com.ll.medium.article.Article;
import com.ll.medium.article.ArticleService;
import com.ll.medium.member.Member;
import com.ll.medium.member.MemberService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{commentId}/modify")
    public String modifyComment(@PathVariable("articleId") Integer articleId,
                                @PathVariable("commentId") Integer commentId,
                                CommentForm commentForm,
                                Principal principal,
                                Model model){

        Comment comment = commentService.getCommentById(commentId);

        if(!comment.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        model.addAttribute("article",comment.getArticle());
        model.addAttribute("originalComment", comment);
        commentForm.setBody(comment.getBody());
        return "comment_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{commentId}/modify")
    public String modifyComment(@PathVariable("commentId") Integer commentId,
                                @Valid CommentForm commentForm,
                                BindingResult bindingResult,
                                Principal principal){

        Comment comment = commentService.getCommentById(commentId);

        if(bindingResult.hasErrors()){
            return "redirect:/post/"+comment.getArticle().getId();
        }
        if(!comment.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        commentService.modifyComment(comment,commentForm);
        return "redirect:/post/"+comment.getArticle().getId();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") Integer commentId,
                                @PathVariable("articleId") Integer articleId,
                                Principal principal){
        return "";
    }

}
