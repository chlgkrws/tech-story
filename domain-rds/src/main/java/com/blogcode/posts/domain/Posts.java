package com.blogcode.posts.domain;

import com.blogcode.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "posts")
    private List<Replys> replysList = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<HashTag> hashTags = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<Likes> likesList = new ArrayList<>();
}
