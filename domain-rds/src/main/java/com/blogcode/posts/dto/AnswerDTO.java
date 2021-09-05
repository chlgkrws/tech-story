package com.blogcode.posts.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AnswerDTO {

    private Long upperId;

    private Integer depth;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String writerEmail;

    private String writerName;

    private Integer likes;

    @NotNull
    private Long postId;

    @NotNull
    private Long memberId;
}
