package com.ll.medium.article.dto;

import com.ll.medium.article.entity.Article;
import com.ll.medium.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.List;

@Getter
@Setter
public class ArticleResponseForm {
    private String title;
    private String body;
    private String writer;
    private Long hit;
    private Integer like;
    private List<Comment> commentList;

    public ArticleResponseForm(Article article){
        this.title = article.getTitle();
        this.body = article.getBody();
        this.writer = article.getWriter();
        this.hit = article.getHit();
        this.like = article.getLike().size();
        this.commentList = article.getCommentList();
    }
}
