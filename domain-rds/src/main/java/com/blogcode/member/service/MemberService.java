package com.blogcode.member.service;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

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
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long signUp (Member member) {
        return memberRepository.save(member).getId();
    }

    public String register(String email, String password){
        Optional<Member> findMember = this.memberRepository.findByEmail(email);
        //null
        if(findMember.isEmpty()){
            // 난수 삽입
            // 이메일
            return "이메일로 회원가입 링크를 보냈습니다.";
        }else{
            //expired
            if(findMember.get().getEmailPath().length() > 0){    //DB존재-사용안함
                // 난수 업데이트
                // 이메일
                return "이메일로 회원가입 링크를 보냈습니다.";
            }

            //무시
            return "이미 회원가입 된 사용자입니다."; //DB존재- 사용 중
        }
    }

}
