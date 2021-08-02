package com.blogcode.posts.dto;

import com.blogcode.posts.domain.PostType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaDTO {

    @NotEmpty
    private String title;

    @NotNull
    private String content;

    private String thumbnailPath;

    @NotNull
    private String tempSaveStatus;

    @NotNull
    private PostType dType;

    @NotNull
    private Long memberId;

    private List<String> hashTag = new ArrayList<>();
}
