package com.blogcode.mapper;

import com.blogcode.posts.domain.Posts;

import java.util.List;
import java.util.Optional;

/**
 * Description : mapstruct interface (Entity <-> Dto)
 *
 * @author leejinho
 * @version 1.0
 */
public interface StructMapper <Dto, Entity> {
    Dto toDto(Optional<Posts> entity);
    Entity toEntity(Dto dto);
    List<Dto> toDtoList(List<Entity> entityList);
    List<Entity> toEntityList(List<Dto> dtoList);
}
