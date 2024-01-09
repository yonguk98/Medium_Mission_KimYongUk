package com.ll.medium.comment;

import com.ll.medium.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentRestController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<List> getComment(@PathVariable Integer articleId){
        return new ResponseEntity(commentService.getCommentListByArticle(articleService.getArticleById(articleId)), HttpStatus.OK);
    }
}
