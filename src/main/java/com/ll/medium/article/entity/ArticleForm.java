package com.ll.medium.article.entity;

import com.ll.medium.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ArticleForm {

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String body;
    private String writer;
    private LocalDateTime dateTime = LocalDateTime.now();
    private boolean publish = true;
    private boolean isPaid = false;
}
