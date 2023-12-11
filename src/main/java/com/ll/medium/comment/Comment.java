package com.ll.medium.comment;

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
    Long id;

    String body;
    LocalDateTime dateTime;

    @ManyToOne
    Member writer;
}
