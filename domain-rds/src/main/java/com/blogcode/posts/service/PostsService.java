package com.blogcode.posts.service;

import com.blogcode.mapper.BlogMapper;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final BlogMapper blogMapper;

    public Posts findByEmail(Long id){
        Optional<Posts> posts = this.postsRepository.findByWriterEmail(id);

        return posts.orElseThrow(() -> new NoSuchElementException("Member Id: "+ id));
    }

//    public Posts save(Posts posts){
//        return postsRepository.save(posts);
//    }

    public Long deleteBoard(Long id) {
        postsRepository.deleteById(id);
        return id;
    }
}
