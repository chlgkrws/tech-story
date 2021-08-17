package com.blogcode.mapper;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Description : blotDto <-> Posts
 *
 * @author leejinho
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface BlogMapper extends StructMapper<BlogDto, Posts> {
//    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

//    < IF not use extends, add this code >
    BlogDto toDto(Posts posts);
    Posts toEntity(BlogDto blogDto);
    List<BlogDto> toDtoList(List<Posts> posts);
    List<Posts> toEntityList(List<BlogDto> blogDtos);
}
