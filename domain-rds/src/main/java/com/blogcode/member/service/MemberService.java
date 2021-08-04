package com.blogcode.member.service;

import com.blogcode.dto.MailDto;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.utils.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * com.blogcode.com.blogcode.service
 *
 * Description :
 * </pre>
 *
 * @author leejinho
 * @version 1.0
 * @see <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일     수정자   수정내용
 * ---------- -------- -------------------
 * 2021.07.05 leejinho 최초 생성
 * </pre>
 * @since 2021.07.05
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmailSender emailSender;


    public Long signUp (Member member) {
        return memberRepository.save(member).getId();
    }


    public String createRegisterEmail(String receiverEmail) {
        String result = "0";

        MailDto mailMessage = new MailDto();
        mailMessage.setEmailAddress(receiverEmail);
        mailMessage.setTitle("회원가입 이메일 인증");
        mailMessage.setMessage("회원가입 이메일 인증 내용"); //TODO : 회원가입 링크로 변경
        emailSender.sendMail(mailMessage);

        return result;
    }
}
