package com.blogcode.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

   // @Autowired
    //private MemberRepository memberRepository;

//    @Test
//    public void addMember () {
//        Member member = new Member();
//        member.setEmail("zeeno@gsitm.com");
//        member.setPassword("itm@6700");
//        memberRepository.save(member);
//        Member saved = memberRepository.findById(1L).orElse(null);
//        assertThat(saved.getEmail(), is("zeeno@gsitm.com"));
//    }
}
