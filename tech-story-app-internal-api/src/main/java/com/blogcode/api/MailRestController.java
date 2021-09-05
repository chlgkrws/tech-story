package com.blogcode.api;

import com.blogcode.dto.MailDto;
import com.blogcode.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailRestController {
    private final MemberService memberService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public void sendEmail(MailDto mailDto) {
        memberService.createRegisterEmail(mailDto.getEmailAddress());
    }
}
