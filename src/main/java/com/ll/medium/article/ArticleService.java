package com.ll.medium.article;


import com.ll.medium.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Article getArticleById(Integer id){
        Optional<Article> article = articleRepository.getArticleById(id);

        if(!article.isPresent()){
            throw new DataNotFoundException("해당하는 게시글이 없습니다.");
        }
        return article.get();
    }
}
