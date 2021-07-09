package com.blogcode.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <pre>
 * com.blogcode.com.blogcode.domain
 *
 * Description :
 * </pre>
 *
 * @author leejinho
 * @version 1.0
 * @see <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일     수정자   수정내용
 * ---------- -------- -------------------
 * 2021.07.05 leejinho 최초 생성
 * </pre>
 * @since 2021.07.05
 */
@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String emailPath;

    private String oauthType;

    private String profileImgPath;

    private String introduce;
}
