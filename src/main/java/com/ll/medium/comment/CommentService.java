package com.ll.medium.comment;

import com.ll.medium.DataNotFoundException;
import com.ll.medium.article.Article;
import com.ll.medium.article.ArticleForm;
import com.ll.medium.member.Member;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment getCommentById(Integer id){
        Optional<Comment> commentOp = commentRepository.findById(id);
        if(!commentOp.isPresent()){
            throw new DataNotFoundException("아이디에 해당하는 댓글이 존재하지 않습니다");
        }

        return commentOp.get();
    }
    public List<Comment> getCommentListByArticle(Article article){
        return commentRepository.findAllByArticle(article);
    }

    public void createComment(CommentForm commentForm, Article article, Member member){
        Comment comment = Comment.builder()
                .body(commentForm.getBody())
                .dateTime(commentForm.getLocalDateTime())
                .writer(member)
                .article(article)
                .build();
        commentRepository.save(comment);
    }

    public void modifyComment(Comment comment, CommentForm commentForm){
        commentRepository.save(comment.toBuilder()
                .body(commentForm.getBody())
                .dateTime(LocalDateTime.now())
                .build());
    }

    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }
}
