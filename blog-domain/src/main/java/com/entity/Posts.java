package com.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Posts {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(insertable = false, updatable = false, length = 100, unique = true)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
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

    private Long countScripting;

    private Byte tempSaveStatus;

    @Enumerated(EnumType.STRING)
    private PostType dType;
}
