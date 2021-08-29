package com.blogcode.api.qna;

import com.blogcode.posts.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/answer", produces = MediaTypes.HAL_JSON_VALUE)
public class AnswerController {

    // TODO answer 조회
    @GetMapping("/{postId}")
    public ResponseEntity queryAnswer(Reply reply, @PathVariable Long postId){

        return ResponseEntity.ok().build();
    }

    // TODO answer 생성
    @PostMapping
    public ResponseEntity createAnswer(Reply reply){

        return ResponseEntity.ok().build();
    }

    // TODO answer 수정
    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO answer 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
}
