package com.blogcode;

import com.blogcode.domain.Member;
import com.blogcode.service.MemberService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleApiApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void save() {
        Member member = new Member("zeeno", "zeeno@gsitm.com");
        Long id = memberService.signUp(member);
        assertThat(id, is(1L));
    }
}
