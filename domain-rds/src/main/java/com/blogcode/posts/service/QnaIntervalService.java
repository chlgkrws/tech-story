package com.blogcode.posts.service;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class QnaIntervalService {

    private final QnaRepository qnaRepository;

    public Page<Posts> findByPostList(String interval, Pageable pageable) {

        return this.qnaRepository.findAll(pageable);
    }
}
