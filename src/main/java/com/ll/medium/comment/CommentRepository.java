package com.ll.medium.comment;

import com.ll.medium.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Integer id);

    List<Comment> findAllByArticle(Article article);
}
