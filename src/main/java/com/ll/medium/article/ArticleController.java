package com.ll.medium.article;

import com.ll.medium.article.entity.Article;
import com.ll.medium.article.entity.ArticleForm;
import com.ll.medium.comment.entity.CommentForm;
import com.ll.medium.member.entity.Member;
import com.ll.medium.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page){
        Page<Article> articles = articleService.getPublishedPageList(page);
        model.addAttribute("articleList",articles);
        return "article_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myList")
    public String myList(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal){
        Member member = memberService.getMemberByUsername(principal.getName());
        Page<Article> articles = articleService.getAllArticlesByUsername(page, member);
        model.addAttribute("articleList",articles);
        return "article_mylist";
    }
    @GetMapping("/write")
    public String create(ArticleForm articleForm){
        return "article_form";
    }

    @PostMapping("/write")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "article_form";
        }

        try{
            Member member = memberService.getMemberByUsername(principal.getName());
            articleForm.setWriter(member.getUsername());
        } catch (NullPointerException e){
            articleForm.setWriter(null);
        }
        articleService.createArticle(articleForm);

        return "redirect:/post/list";
    }

    @GetMapping("/{articleId}")
    public String articleDetail(@PathVariable("articleId") Integer articleId, Model model, CommentForm commentForm, Principal principal){
        Article article = articleService.getArticleById(articleId);
        articleService.addHit(article);
        model.addAttribute("article", article);

        try{
            Member loginedMember = memberService.getMemberByUsername(principal.getName());
            model.addAttribute("member",loginedMember);
        }catch (NullPointerException e){
            model.addAttribute("member",null);
        }

        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{articleId}/modify")
    public String modifyArticle(@PathVariable("articleId") Integer articleId, Principal principal, ArticleForm articleForm){
        Article article = articleService.getArticleById(articleId);

        if(!article.getWriter().equals(principal.getName())){
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

        if(!article.getWriter().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        articleService.modify(article, articleForm);

        return String.format("redirect:/post/%s",articleId);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable("articleId") Integer id, Principal principal, ArticleForm articleForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/post/"+id;
        }

        Article article = articleService.getArticleById(id);

        if(!article.getWriter().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        articleService.deleteArticle(article);

        return "redirect:/post/list";
    }

    @GetMapping("/b/{username}")
    public String articlesByUsername(@PathVariable("username") String username, Model model){
        Member writer = memberService.getMemberByUsername(username);

        List<Article> articles = articleService.getAllArticlesByWriter(writer);

        if(articles.size()==0){
            return "redirect:/post/list";
        }
        model.addAttribute("articleList",articles);
        return "article_list";
    }

    @GetMapping("/b/{username}/{articleId}")
    public String articleDetailByUsernameAndArticleId(@PathVariable("username") String username, @PathVariable("articleId") Integer articleId, Model model){
        Member writer = memberService.getMemberByUsername(username);

        List<Article> articles = articleService.getAllArticlesByWriter(writer);
        if(articles.size()==0){
            return "redirect:/post/list";
        }
        model.addAttribute("article", articles.get(articleId-1));
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{articleId}/like")
    public String articleLike(@PathVariable("articleId") Integer id, Principal principal){
        Article article = articleService.getArticleById(id);
        Member member = memberService.getMemberByUsername(principal.getName());
        articleService.addLike(article,member);
        return "redirect:/post/"+id;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{articleId}/likeCancel")
    public String articleLikeCancel(@PathVariable("articleId") Integer id, Principal principal){
        Article article = articleService.getArticleById(id);
        Member member = memberService.getMemberByUsername(principal.getName());
        articleService.subLike(article,member);
        return "redirect:/post/"+id;
    }

//    @PostMapping("/{articleId}/increaseHit")
//    public String increaseHit(@PathVariable("articleId") Integer id, Model model){
//        articleService.addHit(articleService.getArticleById(id));
//        return "redirect:/post/"+id;
//    }

}
