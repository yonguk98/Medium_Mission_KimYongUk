package com.ll.medium.article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public void createArticle(ArticleForm articleForm){
        Article article = Article.builder()
                .title(articleForm.getTitle())
                .body(articleForm.getBody())
                .writer(articleForm.getWriter())
                .isPublished(articleForm.isPublished())
                .dateTime(LocalDateTime.now())
                .build();

        articleRepository.save(article);
    }
}
