package com.blogcode.posts.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashTagDTO {

    private Long id;

    @NotNull
    private String keyword;

    private Long postsId;

}
