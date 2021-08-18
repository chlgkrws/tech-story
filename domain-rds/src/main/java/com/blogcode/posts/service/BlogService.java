package com.blogcode.posts.service;

import com.blogcode.mapper.BlogMapper;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public Page<Posts> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public BlogDto saveByDto(BlogDto blogDto){
        Posts savedPosts = blogRepository.save(blogMapper.toEntity(blogDto));
        return blogMapper.toDto(Optional.of(savedPosts));
    }

    public BlogDto findBlogById(Long id) {
        return blogMapper.toDto(blogRepository.findById(id));
    }

    public Long deleteBoard(Long id) {
        blogRepository.deleteById(id);
        return id;
    }
}
