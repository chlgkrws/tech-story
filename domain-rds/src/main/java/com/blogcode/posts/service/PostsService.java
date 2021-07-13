package com.blogcode.posts.service;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public Posts findByEmail(Long id){
        Optional<Posts> posts = this.postsRepository.findByEmail(id);

        return posts.orElseThrow(() -> new NoSuchElementException("Member Id: "+ id));
    }
}
