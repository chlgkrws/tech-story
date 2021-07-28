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
public class QnaDTO {

    @NotEmpty
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String thumbnailPath;

    @NotNull
    private String tempSaveStatus;

    @NotNull
    private PostType dType;

    @NotNull
    private Long memberId;
}
