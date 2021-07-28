package com.blogcode.api.qna;

import com.blogcode.posts.dto.PostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("postDTO 생성 및 응답")
    public void createPostDto() throws Exception {
        PostDTO postDTO = PostDTO.builder()
                .title("hi")
                .build();

        this.mockMvc.perform(post("/api/qna")
            .content(String.valueOf(postDTO))
        )
                .andDo(print());
    }
}
