package com.blogcode.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Replys {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long upperId;

    @Column
    @ColumnDefault("0")
    private Integer depth;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 50)
    private String writerEmail;

    @Column(length = 50)
    private String writerName;

    @Column
    @ColumnDefault("0")
    private Integer likes;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
