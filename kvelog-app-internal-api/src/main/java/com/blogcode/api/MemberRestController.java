package com.blogcode.api;

import com.blogcode.member.domain.Member;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * k-velog
 *
 * Description :
 * </pre>
 *
 * @author LeeJH
 * @since 2021-07-05
 */
@RestController
@RequestMapping(value = "/api/member", produces = MediaTypes.HAL_JSON_VALUE)
public class MemberRestController {

    // TODO 멤버 리스트 조회
    @GetMapping
    public ResponseEntity queryMembers(){

        return ResponseEntity.ok().build();
    }
    // TODO 멤버 조회
    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
    // TODO 멤버 생성
    @PostMapping
    public ResponseEntity createMember(Member member){

        return ResponseEntity.ok().build();
    }
    // TODO 멤버 수정
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }
    // TODO 멤버 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }


}
