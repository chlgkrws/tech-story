package com.blogcode.config;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OAuthTokenTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("토큰 발급 받기")
    public void getToken() throws Exception {
        // Given
        String username = "cg32421421432w981@naver.com31";
        String password = "pass";
        Member hakjun  = Member.builder()
                .email(username)
                .password(passwordEncoder.encode(password))
                .build();
        this.memberRepository.save(hakjun);

        String clientId = "kvelog";
        String clientSecret = "kvsecret";
        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .param("username", username)
                .param("password", password)
                .param("grant_type","password")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
                .andDo(print())
        ;


    }



}
