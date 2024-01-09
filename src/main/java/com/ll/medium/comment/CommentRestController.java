package com.ll.medium.comment;

import com.ll.medium.article.ArticleService;
import com.ll.medium.comment.entity.Comment;
import com.ll.medium.comment.entity.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article/{articleId}/comment")
public class CommentRestController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List> getComment(@PathVariable Integer articleId){
        return new ResponseEntity(commentService.getCommentListByArticle(articleService.getArticleById(articleId)), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity postComment(@PathVariable Integer articleId, @RequestBody CommentForm commentForm, Principal principal){
        try {
            commentService.createComment(commentForm, articleService.getArticleById(articleId), principal.getName());
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{commentId}")
    public ResponseEntity modifyComment(@PathVariable Integer articleId,
                                        @PathVariable Integer commentId ,
                                        @RequestBody CommentForm commentForm,
                                        Principal principal) {

        Comment comment = commentService.getCommentById(commentId);

        if(comment.getWriter().equals(principal.getName())){
            commentService.modifyComment(comment,commentForm);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer articleId,
                                        @PathVariable Integer commentId ,
                                        Principal principal) {
        Comment comment = commentService.getCommentById(commentId);
        if(comment.getWriter().equals(principal.getName())){
            commentService.deleteComment(comment);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
