package com.blogcode.mapper;

import com.blogcode.posts.domain.Posts;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

/**
 * Description : mapstruct interface (Entity <-> Dto)
 *
 * @author leejinho
 * @version 1.0
 */
public interface StructMapper <Dto, Entity> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
    List<Dto> toDtoList(List<Entity> entityList);
    List<Entity> toEntityList(List<Dto> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(Dto dto, @MappingTarget Entity entity);
}
