package com.blogcode.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <pre>
 * PACKAGE_NAME
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
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    public void add () {
//        memberRepository.save(new Member("jinho", "zeeno@gsitm.com"));
//        Member saved = memberRepository.findOne(1L);
//        assertThat(saved.getName(), is("jojoldu"));
//    }
}
