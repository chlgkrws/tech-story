package com.blogcode.api;

import com.blogcode.common.RestDocsConfiguration;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.repository.PostsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
public class BlogRestControllerTest {
    
    // TODO 로그인 기능이 완성된 후 토큰 방식으로 Member id를 활용한 테스트 케이스 추가 필요
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("블로그 글 생성 성공")
    @Disabled
    public void createBlogContent() throws Exception{
        // Given
        Member member = getRandomMember();

        Posts posts = Posts.builder()
                .title("블로그 글 생성")
                .content("테스트")
                .writerEmail(member.getEmail())
                .writerName("최학준")
                .thumbnailPath("/qwdqw/dwqdwq/dwqdwq")
                .dType(PostType.BLOG)
                .member(member)
                .build();


            // Then
            this.mockMvc.perform(post("/api/blog")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(posts))
                )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(header().exists(HttpHeaders.LOCATION))
                    .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("id").exists())
                    .andExpect(jsonPath("dtype").exists())
                    .andExpect(jsonPath("member.id").exists())
                    .andExpect(jsonPath("member.email").exists())
                    .andDo(document("create-blog",
                            links(
                                    linkWithRel("self").description("생성된 Blog 조회"),
                                    linkWithRel("query-blogs").description("Blog 목록 조회"),
                                    linkWithRel("update-blog").description("생성된 Blog 수정"),
                                    linkWithRel("profile").description("해당 Rest API profile 이동")
                            ),
                            requestHeaders(
                                    headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                                    headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                            ),
                            relaxedRequestFields(
                                    fieldWithPath("title").description("생성할 Blog의 제목"),
                                    fieldWithPath("content").description("생성할 Blog 내용"),
                                    fieldWithPath("writerEmail").description("생성할 Blog 작성자 이메일"),
                                    fieldWithPath("writerName").description("생성할 Blog의 작성자 이름"),
                                    fieldWithPath("tempSaveStatus").description("생성할 Blog의 임시저장 여부"),
                                    fieldWithPath("dtype").description("생성할 Blog의 타입(블로그/Q&A)")
                            ),
                            responseHeaders(
                                    headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json"),
                                    headerWithName(HttpHeaders.LOCATION).description("응답 Location")
                            ),
                            relaxedResponseFields(
                                    fieldWithPath("id").description("생성된 Blog의 식별자"),
                                    fieldWithPath("title").description("생성된 Blog의 제목"),
                                    fieldWithPath("content").description("생성된 Blog의 내용"),
                                    fieldWithPath("writerEmail").description("생성된 Blog 작성자 이메일"),
                                    fieldWithPath("writerName").description("생성된 Blog의 작성자 이름"),
                                    fieldWithPath("thumbnailPath").description("생성된 Blog의 썸네일 경로"),
                                    fieldWithPath("tempSaveStatus").description("생성된 Blog의 임시저장여부"),
                                    fieldWithPath("member.id").description("생성된 Blog의 작성자 식별자"),
                                    fieldWithPath("dtype").description("생성된 Blog의 타입(블로그/Q&A)")
                            )
                    ))
        ;

    }

    private Member getRandomMember(){
        int number = (int) (Math.random() * 100);
        Member member = Member.builder()
                .email("cgw" +number+ "@naver.com")
                .name("최학준")
                .password("hi")
                .build();
        member.setCreateDateTime(LocalDateTime.of(2022,11,11,10,10,10));
        member = this.memberRepository.save(member);
        member.setCreateDateTime(LocalDateTime.of(2022,11,11,10,10,10));
        member = this.memberRepository.save(member);
        System.out.println(member.getId());
        return member;
    }
}
