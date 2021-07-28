package com.blogcode.api.qna;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.member.service.MemberService;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class QnaTest {

    @Test
    @DisplayName("Qna DTO 생성")
    public void createQnaDto(){
        QnaDTO qnaDTO = QnaDTO.builder()
                .title("테스트 글")
                .content("테스트")
                .thumbnailPath("/")
                .tempSaveStatus("Y")
                .dType(PostType.QNA)
                .memberId(10L)
                .build();

        Assertions.assertThat(qnaDTO).isNotNull();
    }
}
