package com.blogcode.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String emailPath;

    @Column(length = 30)
    private String oauthType;

    @Column(length = 200)
    private String profileImgPath;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @OneToMany(mappedBy = "member")
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Replys> replysList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<UserRole> userRolerList = new ArrayList<>();
}
