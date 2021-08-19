package com.blogcode.mapper;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-19T14:29:06+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public void updateFromDto(BlogDto dto, Posts entity) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public BlogDto toDto(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        BlogDto blogDto = new BlogDto();

        return blogDto;
    }

    @Override
    public Posts toEntity(BlogDto blogDto) {
        if ( blogDto == null ) {
            return null;
        }

        Posts posts = new Posts();

        return posts;
    }

    @Override
    public List<BlogDto> toDtoList(List<Posts> posts) {
        if ( posts == null ) {
            return null;
        }

        List<BlogDto> list = new ArrayList<BlogDto>( posts.size() );
        for ( Posts posts1 : posts ) {
            list.add( toDto( posts1 ) );
        }

        return list;
    }

    @Override
    public List<Posts> toEntityList(List<BlogDto> blogDtos) {
        if ( blogDtos == null ) {
            return null;
        }

        List<Posts> list = new ArrayList<Posts>( blogDtos.size() );
        for ( BlogDto blogDto : blogDtos ) {
            list.add( toEntity( blogDto ) );
        }

        return list;
    }
}
