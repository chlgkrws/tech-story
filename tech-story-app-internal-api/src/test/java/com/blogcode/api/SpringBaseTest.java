package com.blogcode.api;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class SpringBaseTest {

    @Autowired
    MemberRepository memberRepository;

    public Member getRandomMember(){
        Integer ints = new Random().nextInt(1000);
        String email = "zee12"+ ints +"@gstim.com";
        String password = "itm@6700";

        Member member = Member.builder()
                .email(email)
                .password(password)
                .name("이진호")
                .build();

        this.memberRepository.save(member);

        return member;
    }
}
