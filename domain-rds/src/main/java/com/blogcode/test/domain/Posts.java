package com.blogcode.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Posts {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(insertable = false, updatable = false, length = 100)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 50)
    private String writerEmail;

    @Column(length = 50)
    private String writerName;

    @Column(insertable = false)
    @ColumnDefault("0")
    private Long views;

    @Column(insertable = false)
    @ColumnDefault("0")
    private Long likes;

    @Column(length = 200)
    private String thumbnailPath;

    @ColumnDefault("0")
    private Long countScripting;

    @ColumnDefault("0")
    private Byte tempSaveStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PostType dType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
