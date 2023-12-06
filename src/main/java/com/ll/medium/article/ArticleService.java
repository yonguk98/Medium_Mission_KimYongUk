package com.ll.medium.article;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }
}
