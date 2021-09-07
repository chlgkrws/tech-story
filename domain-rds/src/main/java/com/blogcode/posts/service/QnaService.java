package com.blogcode.posts.service;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    private final QnaIntervalService qnaIntervalService;

    public Page<Posts> findByPostList(String interval, String search, Pageable pageable) {

        if(interval == null && search != null){
            return findByPostList(search, pageable);
        }

        return this.qnaIntervalService.findByPostList(interval, pageable);
    }

    // TODO with search
    public Page<Posts> findByPostList(String search, Pageable pageable) {

        return this.qnaRepository.findAll(pageable);
    }

    // 조회 수 증가
    public void increaseOneViewCount(Long id) {
        this.qnaRepository.increaseOneViews(id);
    }

    // TODO MapStruct
    public void qnaToEntity(QnaDTO qnaDTO, Posts posts) {
        posts.setTitle(qnaDTO.getTitle());
        posts.setContent(qnaDTO.getContent());
        posts.setThumbnailPath(qnaDTO.getThumbnailPath());
        posts.setTempSaveStatus(qnaDTO.getTempSaveStatus());
        posts.setDType(qnaDTO.getDType());
    }


}
