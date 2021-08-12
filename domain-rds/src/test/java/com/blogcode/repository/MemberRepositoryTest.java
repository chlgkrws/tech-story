package com.blogcode.repository;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * <pre>
 * com.blogcode.repository
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
 * 2021.07.06 leejinho 최초 생성
 * </pre>
 * @since 2021.07.06
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void addMember () {
        String email = "zeeno@gstim.com";
        String password = "itm@6700";
        Member member = Member.builder()
                .email(email)
                .password(password)
                .build();

        Member saved = memberRepository.save(member);

        Assertions.assertThat(email).isEqualTo(saved.getEmail());

//        Member member = new Member();
//        member.setEmail("zeeno@gsitm.com");
//        member.setPassword("itm@6700");
//        memberRepository.save(member);
//        Member saved = memberRepository.findById(1L).orElse(null);
//        assertThat(saved.getEmail(), is("zeeno@gsitm.com"));
    }
}
