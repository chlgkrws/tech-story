package com.blogcode.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
}
