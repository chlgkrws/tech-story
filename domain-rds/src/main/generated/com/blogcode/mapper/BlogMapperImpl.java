package com.blogcode.mapper;

import com.blogcode.posts.domain.HashTag;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.domain.Posts.PostsBuilder;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.dto.BlogDto.BlogDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-19T15:03:11+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public BlogDto toDto(Posts arg0) {
        if ( arg0 == null ) {
            return null;
        }

        BlogDtoBuilder blogDto = BlogDto.builder();

        blogDto.id( arg0.getId() );
        blogDto.title( arg0.getTitle() );
        blogDto.content( arg0.getContent() );
        blogDto.writerName( arg0.getWriterName() );
        blogDto.tempSaveStatus( arg0.getTempSaveStatus() );
        blogDto.thumbnailPath( arg0.getThumbnailPath() );
        blogDto.likes( arg0.getLikes() );
        List<HashTag> list = arg0.getHashTags();
        if ( list != null ) {
            blogDto.hashTags( new ArrayList<HashTag>( list ) );
        }

        return blogDto.build();
    }

    @Override
    public Posts toEntity(BlogDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        PostsBuilder posts = Posts.builder();

        posts.id( arg0.getId() );
        posts.title( arg0.getTitle() );
        posts.content( arg0.getContent() );
        posts.writerName( arg0.getWriterName() );
        posts.likes( arg0.getLikes() );
        posts.thumbnailPath( arg0.getThumbnailPath() );
        posts.tempSaveStatus( arg0.getTempSaveStatus() );
        List<HashTag> list = arg0.getHashTags();
        if ( list != null ) {
            posts.hashTags( new ArrayList<HashTag>( list ) );
        }

        return posts.build();
    }

    @Override
    public List<BlogDto> toDtoList(List<Posts> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<BlogDto> list = new ArrayList<BlogDto>( arg0.size() );
        for ( Posts posts : arg0 ) {
            list.add( toDto( posts ) );
        }

        return list;
    }

    @Override
    public List<Posts> toEntityList(List<BlogDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Posts> list = new ArrayList<Posts>( arg0.size() );
        for ( BlogDto blogDto : arg0 ) {
            list.add( toEntity( blogDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(BlogDto arg0, Posts arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getTitle() != null ) {
            arg1.setTitle( arg0.getTitle() );
        }
        if ( arg0.getContent() != null ) {
            arg1.setContent( arg0.getContent() );
        }
        if ( arg0.getWriterName() != null ) {
            arg1.setWriterName( arg0.getWriterName() );
        }
        if ( arg0.getLikes() != null ) {
            arg1.setLikes( arg0.getLikes() );
        }
        if ( arg0.getThumbnailPath() != null ) {
            arg1.setThumbnailPath( arg0.getThumbnailPath() );
        }
        if ( arg0.getTempSaveStatus() != null ) {
            arg1.setTempSaveStatus( arg0.getTempSaveStatus() );
        }
        if ( arg0.getDType() != null ) {
            arg1.setDType( arg0.getDType() );
        }
        if ( arg1.getHashTags() != null ) {
            List<HashTag> list = arg0.getHashTags();
            if ( list != null ) {
                arg1.getHashTags().clear();
                arg1.getHashTags().addAll( list );
            }
        }
        else {
            List<HashTag> list = arg0.getHashTags();
            if ( list != null ) {
                arg1.setHashTags( new ArrayList<HashTag>( list ) );
            }
        }
    }
}
