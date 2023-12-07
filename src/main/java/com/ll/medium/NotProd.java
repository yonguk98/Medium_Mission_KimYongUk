package com.ll.medium;

import com.ll.medium.article.ArticleForm;
import com.ll.medium.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final ArticleService articleService;

    @Bean
    public ApplicationRunner initNotProd() {
        ArticleForm articleForm = new ArticleForm();
        articleForm.setTitle("title1");
        articleForm.setBody("body1");

        return args -> {
            articleService.createArticle(articleForm);
        };
    }
}
