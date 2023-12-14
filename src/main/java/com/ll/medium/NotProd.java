package com.ll.medium;

import com.ll.medium.article.entity.ArticleForm;
import com.ll.medium.article.ArticleService;
import com.ll.medium.member.entity.MemberCreateForm;
import com.ll.medium.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final ArticleService articleService;
    private final MemberService memberService;

    @Bean
    public ApplicationRunner initNotProd() {
        for (int i = 1; i < 31; i++) {
            MemberCreateForm memberCreateForm = new MemberCreateForm();
            memberCreateForm.setUsername("user"+i);
            memberCreateForm.setPassword("1234");
            memberService.create(memberCreateForm);

            ArticleForm articleForm = new ArticleForm();
            articleForm.setTitle("title"+i);
            articleForm.setBody("body"+i);
            articleForm.setWriter(memberService.getMemberByUsername("user"+i));
            articleService.createArticle(articleForm);
        }
        return args -> {};
    }
}
