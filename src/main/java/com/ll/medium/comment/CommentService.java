package com.ll.medium.comment;

import com.ll.medium.global.DataNotFoundException;
import com.ll.medium.article.entity.Article;
import com.ll.medium.comment.entity.Comment;
import com.ll.medium.comment.entity.CommentForm;
import com.ll.medium.member.entity.Member;
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

    public void createComment(CommentForm commentForm, Article article, String writer){
        Comment comment = Comment.builder()
                .body(commentForm.getBody())
                .dateTime(commentForm.getLocalDateTime())
                .writer(writer)
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
