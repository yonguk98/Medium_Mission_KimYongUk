package com.ll.medium.article;

import com.ll.medium.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ArticleForm {

    private String title;
    private String body;
    private Member writer;
    private LocalDateTime dateTime;
    private boolean isPublished;
}
