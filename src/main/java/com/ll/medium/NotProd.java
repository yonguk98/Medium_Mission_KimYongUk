package com.ll.medium;

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
        return args -> {

        };
    }
}
