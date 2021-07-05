package com.blogcode;

import com.blogcode.domain.Member;
import com.blogcode.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@DataJpaTest
public class ModuleApiApplicationTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void save() {
        Member member = new Member("jojoldu", "jojoldu@gmail.com");
        Long id = memberService.signUp(member);
        assertThat(id, is(1L));
    }
}
