package com.ll.medium.comment;

import com.ll.medium.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentForm {
    String body;
    LocalDateTime localDateTime;
    Member writer;
}
