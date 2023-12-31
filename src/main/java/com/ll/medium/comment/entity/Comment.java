package com.ll.medium.comment.entity;

import com.ll.medium.article.entity.Article;
import com.ll.medium.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String body;
    private LocalDateTime dateTime;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Member writer;
}
