package com.blogcode.mapper;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Description : blotDto <-> Posts
 *
 * @author leejinho
 * @version 1.0
 */
//@Mapper
@Mapper(componentModel = "spring")
public interface BlogMapper extends StructMapper<BlogDto, Posts> {
}
