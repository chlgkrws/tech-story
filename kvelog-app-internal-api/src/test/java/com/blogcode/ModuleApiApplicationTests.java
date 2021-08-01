package com.blogcode;

import com.blogcode.member.domain.Member;
import com.blogcode.member.service.MemberService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * <pre>
 * com.blogcode
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
@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class ModuleApiApplicationTests {
    @Autowired
    private MemberService memberService;

    //@Test
    void addMember() {
        Member member = new Member();
        member.setEmail("zeeno@gsitm.com");
        member.setPassword("itm@6700");
        Long id = memberService.signUp(member);
        assertThat(id, is(1L));
    }
}
