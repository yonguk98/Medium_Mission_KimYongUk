package com.ll.medium.article;

import com.ll.medium.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> getArticleById(Integer id);

    List<Article> findAllByWriter(Member writer);

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByIsPublished(Pageable pageable, Boolean bool);

    Page<Article> findAllByWriter(Pageable pageable, Member writer);
}
