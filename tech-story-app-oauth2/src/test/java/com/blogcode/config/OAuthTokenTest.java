package com.blogcode.config;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        ResultActions resultActions = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
                .andDo(print());

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        Jackson2JsonParser jackson2JsonParser = new Jackson2JsonParser();
        Map<String, Object> stringObjectMap = jackson2JsonParser.parseMap(contentAsString);
        String access_token = stringObjectMap.get("access_token").toString();
        System.out.println(access_token);
    }

    @Test
    @DisplayName("토큰 포함 / 요청 보내기")
    public void requestWithToken() throws Exception {

        // Given
        String token = "3a6c9a7e-8d9c-4f62-aa6e-742cfd8a0730";

        ResultActions resultActions = this.mockMvc.perform(get("/bearer/test")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken2())
        )
                .andDo(print());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertThat(contentAsString).isEqualTo("hi");

    }



    public String getToken2() throws Exception {
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
        ResultActions resultActions = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
                .andDo(print());

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        Jackson2JsonParser jackson2JsonParser = new Jackson2JsonParser();
        Map<String, Object> stringObjectMap = jackson2JsonParser.parseMap(contentAsString);
        String access_token = stringObjectMap.get("access_token").toString();
        return access_token;
    }




}
