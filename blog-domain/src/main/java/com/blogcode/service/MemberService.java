package com.blogcode.service;

import com.blogcode.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.blogcode.repository.MemberRepository;

/**
 * <pre>
 * com.blogcode.service
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
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long signUp (Member member) {
        return memberRepository.save(member).getId();
    }
}
