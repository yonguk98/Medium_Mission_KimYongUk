package com.ll.medium.global;

import com.ll.medium.article.entity.ArticleForm;
import com.ll.medium.article.ArticleService;
import com.ll.medium.member.dto.MemberCreateForm;
import com.ll.medium.member.entity.Member;
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
        int userCount = 10;
        for (int i = 0; i < userCount; i++) {
            MemberCreateForm memberCreateForm = new MemberCreateForm();
            memberCreateForm.setUsername("user" + (i + 1));
            memberCreateForm.setPassword("1234");
            Member member = memberService.create(memberCreateForm);
            if (i % 2 == 0) {
                memberService.setMembership(member, true);
            }
        }
        for (int i = 0; i < 100; i++) {
            ArticleForm articleForm = new ArticleForm();
            articleForm.setTitle("title" + (i + 1));
            articleForm.setBody("body" + (i + 1));
            articleForm.setWriter("user" + (i % userCount + 1));
            articleService.createArticle(articleForm);
        }
        return args -> {
        };
    }
}
