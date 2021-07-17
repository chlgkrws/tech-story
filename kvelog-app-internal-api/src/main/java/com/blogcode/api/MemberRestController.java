package com.blogcode.api;

import com.blogcode.member.domain.Member;
import com.blogcode.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/member", produces = MediaTypes.HAL_JSON_VALUE)
public class MemberRestController {

    private final MemberService memberService;

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
    public ResponseEntity createMember(@RequestBody Member member){

        String registerMsg = memberService.register(member.getEmail(), member.getPassword());

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
