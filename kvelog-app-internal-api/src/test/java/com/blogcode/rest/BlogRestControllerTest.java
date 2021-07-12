package com.blogcode.rest;

import com.blogcode.member.domain.Member;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.repository.PostsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class BlogRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostsRepository postsRepository;

//    @Autowired
//    private MemberRepository memberRepository;

    @Test
    @DisplayName("블로그 글 생성 성공")
    public void createBlogContent() throws Exception{
        // Given
        Member member = Member.builder()
                .email("cgw981@naver.com")
                .name("최학준")
                .password("hi")
                .build();

        Posts posts = Posts.builder()
                .title("블로그 글 생성")
                .content("테스트")
                .writerEmail("cgw981@naver.com")
                .writerName("최학준")
                .thumbnailPath("/qwdqw/dwqdwq/dwqdwq")
                .dType(PostType.BLOG)
                .member(member)
                .build();

        // Then
        this.mockMvc.perform(post("/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_FORMS_JSON_VALUE)
                .content(objectMapper.writeValueAsString(posts))
        )
                .andDo(print())
                .andExpect(status().isOk())
        ;

    }
}
