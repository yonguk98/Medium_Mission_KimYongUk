package com.ll.medium.comment.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String body;
    private LocalDateTime localDateTime = LocalDateTime.now();
}
