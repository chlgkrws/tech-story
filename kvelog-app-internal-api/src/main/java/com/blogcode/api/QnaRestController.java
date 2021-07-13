package com.blogcode.api;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/qna", produces = MediaTypes.HAL_JSON_VALUE)
public class QnaRestController {

    private final PostsService postsService;

    // TODO qna 목록 조회
    @GetMapping
    public ResponseEntity queryQna(){

        return ResponseEntity.ok().build();
    }
    // TODO qna 조회
    @GetMapping("/{id}")
    public ResponseEntity getQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
    // TODO qna 생성
    @PostMapping
    public ResponseEntity createQna(Posts posts){

        return ResponseEntity.ok().build();
    }
    // TODO qna 수정
    @PutMapping("/{id}")
    public ResponseEntity updateQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
    // TODO qna 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
}
