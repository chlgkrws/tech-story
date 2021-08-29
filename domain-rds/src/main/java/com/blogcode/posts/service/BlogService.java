package com.blogcode.posts.service;

import com.blogcode.mapper.BlogMapper;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public Page<BlogDto> findAll(String period, String search, Pageable pageable) {
        Page<Posts> postsPage = blogRepository.findAllByTitleContainingOrContentContainingOrHashTagsContaining(search, pageable);
        return toDtoListPage(pageable, postsPage);
    }

    @Transactional
    public BlogDto saveByDto(BlogDto blogDto){
        Posts savedPosts = blogRepository.save(blogMapper.toEntity(blogDto));
        return blogMapper.toDto(savedPosts);
    }

    @Transactional
    public BlogDto update(BlogDto blogDto){
        Posts updatedPosts = blogRepository.save(blogMapper.toEntity(blogDto));
        return blogMapper.toDto(updatedPosts);
    }

    public BlogDto findBlogById(Long id) {
        return blogMapper.toDto(blogRepository.findById(id).get());
    }

    @Transactional
    public Long deleteBoard(Long id) {
        blogRepository.deleteById(id);
        return id;
    }

    Page<BlogDto> toDtoListPage(Pageable pageRequest, Page<Posts> source) {
        List<BlogDto> dtos = blogMapper.toDtoList(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}
