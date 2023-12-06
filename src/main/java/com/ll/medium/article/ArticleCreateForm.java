package com.ll.medium.article;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleCreateForm {

    private String title;
    private String body;
    private boolean isPublished;
}
