package com.ll.medium.comment;

import com.ll.medium.article.Article;
import com.ll.medium.article.ArticleForm;
import com.ll.medium.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void createComment(CommentForm commentForm, Article article, Member member){
        Comment comment = Comment.builder()
                .body(commentForm.getBody())
                .dateTime(commentForm.getLocalDateTime())
                .writer(member)
                .article(article)
                .build();
        commentRepository.save(comment);
    }
}
