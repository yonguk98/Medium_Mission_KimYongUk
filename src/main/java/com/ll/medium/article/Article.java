package com.ll.medium.article;

import com.ll.medium.comment.Comment;
import com.ll.medium.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String body;

    @ManyToOne
    private Member writer;

    private LocalDateTime dateTime;

    @Builder.Default
    private Long hit = 0L;

    @Builder.Default
    private boolean isPublished = true;

    @ManyToMany
    private Set<Member> like;

    @OneToMany
    private Set<Comment> comments;
}
