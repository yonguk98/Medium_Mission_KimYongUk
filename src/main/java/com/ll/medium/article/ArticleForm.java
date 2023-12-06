package com.ll.medium.article;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ArticleForm {

    private String title;
    private String body;
    private String writer;
    private LocalDateTime dateTime;
    private boolean isPublished;
}
