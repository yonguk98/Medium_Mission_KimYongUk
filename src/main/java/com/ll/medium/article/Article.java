package com.ll.medium.article;

import com.ll.medium.member.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String body;

    @ManyToOne
    private Member writer;

    private LocalDateTime dateTime;

    private boolean isPublished;
}
