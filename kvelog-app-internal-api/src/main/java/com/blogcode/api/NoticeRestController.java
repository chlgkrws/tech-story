package com.blogcode.api;

import com.blogcode.posts.dto.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/notice", produces = MediaTypes.HAL_JSON_VALUE)
public class NoticeRestController {

    // TODO 공지사항 페이지 로딩   -    페이지 네이션
    // TODO 검색기능
    // TODO 세부내용 조회
    // TODO 출간하기
    // TODO 임시저장

    @GetMapping("/{page}")
    public ResponseEntity getNoticeList(@PathVariable Integer page){


        // TODO 글작성 페이지 - Front 요청 Location을 포함
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{search}")
    public ResponseEntity searchNotice(@PathVariable String search){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getNotice(@PathVariable Integer id){

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createNotice(@RequestBody @Valid NoticeDTO noticeDto, Error error){

        return ResponseEntity.ok().build();
    }

//    @PostMapping
//    public ResponseBody tempoNotice(@RequestBody @Valid NoticeDTO noticeDTO, Error error){
//
//        return ResponseEntity.ok().build();
//    }

}
