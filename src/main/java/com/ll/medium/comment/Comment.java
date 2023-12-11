package com.ll.medium.comment;

import com.ll.medium.article.Article;
import com.ll.medium.member.Member;
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
