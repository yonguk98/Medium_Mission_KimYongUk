package com.ll.medium.article.entity;

import com.ll.medium.comment.entity.Comment;
import com.ll.medium.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
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

    private String writer;

    private LocalDateTime dateTime;

    @Builder.Default
    private boolean isPaid = false;

    @Builder.Default
    private Long hit = 0L;

    @Builder.Default
    private boolean isPublished = true;

    @ManyToMany
    private Set<Member> like;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;
}
