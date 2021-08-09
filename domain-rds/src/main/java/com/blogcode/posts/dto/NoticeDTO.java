package com.blogcode.posts.dto;

import com.blogcode.posts.domain.Posts;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NoticeDTO {
    private Long id;

    private String title;

    private String content;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
