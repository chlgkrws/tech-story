package com.blogcode.api.qna;

import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.PostsService;
import com.blogcode.posts.service.QnaService;
import com.blogcode.validator.QnaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/qna", produces = MediaTypes.HAL_JSON_VALUE)
public class QnaRestController {

    private final QnaService qnaService;

    private final QnaRepository qnaRepository;

    private final QnaValidator qnaValidator;

    // TODO qna 목록 조회
    @GetMapping
    public ResponseEntity queryQna(){

        return ResponseEntity.ok().build();
    }
    // TODO qna 조회
    @GetMapping("/{id}")
    public ResponseEntity getQna(@PathVariable Long id){

        // TODO qna 조회수

        return ResponseEntity.ok().build();
    }
    // TODO qna 생성
    @PostMapping
    public ResponseEntity createQna(@Valid QnaDTO qnaDTO, Errors errors){

        if(errors.hasErrors()){
            return badRequest();
        }

        qnaValidator.validate(qnaDTO, errors);
        if(errors.hasErrors()){
            return badRequest();
        }




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

    // TODO qna 좋아요
    @PostMapping("/like/{id}")
    public ResponseEntity likeQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO qna 조회수
    public void updatedViews(HttpRequest request){

    }

    // TODO Bad Request 
    public ResponseEntity badRequest(){

        return ResponseEntity.notFound().build();
    }


}
