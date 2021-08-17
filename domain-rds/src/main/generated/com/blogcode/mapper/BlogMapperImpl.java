package com.blogcode.mapper;

import com.blogcode.posts.domain.HashTag;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.domain.Posts.PostsBuilder;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.dto.BlogDto.BlogDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-17T09:15:42+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public BlogDto toDto(Optional<Posts> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        BlogDtoBuilder blogDto = BlogDto.builder();

        return blogDto.build();
    }

    @Override
    public BlogDto toDto(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        BlogDtoBuilder blogDto = BlogDto.builder();

        blogDto.id( posts.getId() );
        blogDto.title( posts.getTitle() );
        blogDto.content( posts.getContent() );
        blogDto.writerName( posts.getWriterName() );
        blogDto.tempSaveStatus( posts.getTempSaveStatus() );
        blogDto.thumbnailPath( posts.getThumbnailPath() );
        blogDto.likes( posts.getLikes() );
        List<HashTag> list = posts.getHashTags();
        if ( list != null ) {
            blogDto.hashTags( new ArrayList<HashTag>( list ) );
        }

        return blogDto.build();
    }

    @Override
    public Posts toEntity(BlogDto blogDto) {
        if ( blogDto == null ) {
            return null;
        }

        PostsBuilder posts = Posts.builder();

        posts.id( blogDto.getId() );
        posts.title( blogDto.getTitle() );
        posts.content( blogDto.getContent() );
        posts.writerName( blogDto.getWriterName() );
        posts.likes( blogDto.getLikes() );
        posts.thumbnailPath( blogDto.getThumbnailPath() );
        posts.tempSaveStatus( blogDto.getTempSaveStatus() );
        List<HashTag> list = blogDto.getHashTags();
        if ( list != null ) {
            posts.hashTags( new ArrayList<HashTag>( list ) );
        }

        return posts.build();
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
