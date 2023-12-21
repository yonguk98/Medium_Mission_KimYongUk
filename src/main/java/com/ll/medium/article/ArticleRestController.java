package com.ll.medium.article;

import com.ll.medium.article.entity.Article;
import com.ll.medium.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public List<Article> list(){
        return articleService.getAllArticles();
    }

}
