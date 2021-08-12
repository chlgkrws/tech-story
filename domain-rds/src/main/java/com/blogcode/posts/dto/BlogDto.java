package com.blogcode.posts.dto;

import com.blogcode.posts.domain.HashTag;
import com.blogcode.posts.domain.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDto {
    private Long id;

    @NotNull(message = "please write title")
    private String title;

    @NotNull(message = "please write content")
    private String content;
    private String writerName;
    private String tempSaveStatus;
    private String thumbnailPath;
    private PostType dType;
    private Long likes;
    private List<HashTag> hashTags = new ArrayList<>();
}