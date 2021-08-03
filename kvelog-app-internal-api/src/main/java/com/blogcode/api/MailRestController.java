package com.blogcode.api;

import com.blogcode.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailRestController {
    private final MemberService memberService;

    @PostMapping
    public void sendEmail(String email) {
        memberService.createRegisterEmail(email);
    }
}
