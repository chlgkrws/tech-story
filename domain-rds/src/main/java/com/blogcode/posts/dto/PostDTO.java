package com.blogcode.posts.dto;

import com.blogcode.posts.domain.PostType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    @NotEmpty
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String writerEmail;

    @NotNull
    private String writerName;

    @NotNull
    private Long likes;

    @NotNull
    private String thumbnailPath;

    @NotNull
    private Long countScripting;

    @NotNull
    private Byte tempSaveStatus;

    @NotEmpty
    private PostType dType;

    @NotEmpty
    private Long memberId;
}
