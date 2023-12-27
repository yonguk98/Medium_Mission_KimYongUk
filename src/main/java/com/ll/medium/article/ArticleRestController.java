package com.ll.medium.article;

import com.ll.medium.article.entity.Article;
import com.ll.medium.article.entity.ArticleForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public List<Article> list(){
        return articleService.getAllArticles();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public ResponseEntity writeArticle(@RequestBody ArticleForm articleForm, Principal principal){

        articleForm.setWriter(principal.getName());
        articleService.createArticle(articleForm);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> articleDetail(@PathVariable Integer articleId){

        Article article = articleService.getArticleById(articleId);
        articleService.addHit(article);

        return new ResponseEntity(article, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{articleId}")
    public ResponseEntity articleModify(@PathVariable Integer articleId, ArticleForm articleForm, Principal principal){

        Article article = articleService.getArticleById(articleId);

        if(!article.getWriter().equals(principal.getName())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        articleService.modify(article,articleForm);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{articleId}")
    public ResponseEntity articleDelete(@PathVariable Integer articleId, Principal principal){

        Article article = articleService.getArticleById(articleId);

        if(!article.getWriter().equals(principal.getName())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        articleService.deleteArticle(article);
        return new ResponseEntity(HttpStatus.OK);
    }

}
