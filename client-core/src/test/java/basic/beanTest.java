package basic;

import com.blogcode.domain.Member;
import com.blogcode.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class beanTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Member 저장 성공 테스트")
    public void createMember(){
        Member member = Member.builder()
                .email("cgw981@naver.com")
                .password("gkrwns")
                .build();

        Member newMember = this.memberRepository.save(member);
        assertThat(newMember.getId(), equalTo(1));
    }
}
