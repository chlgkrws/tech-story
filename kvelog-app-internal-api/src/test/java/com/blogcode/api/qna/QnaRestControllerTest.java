package com.blogcode.api.qna;

import com.blogcode.InternalApiApplication;
import com.blogcode.common.RestDocsConfiguration;
import com.blogcode.member.domain.Member;
import com.blogcode.member.domain.RoleType;
import com.blogcode.member.domain.UserRole;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
class QnaRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    QnaService qnaService;

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Qna 생성")
    public void createQna() throws Exception{
        Member member = getRandomMember();
        System.out.println("member = " + member.getId());

        QnaDTO qnaDTO = QnaDTO.builder()
                .title("테스트 글")
                .content("테스트")
                .thumbnailPath("/")
                .tempSaveStatus("N")
                .dType(PostType.QNA)
                .memberId(member.getId())
                .build();

        this.mockMvc.perform(post("/api/qna")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(qnaDTO))
        )
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("dtype").exists())
                .andExpect(jsonPath("createId").exists())
                .andDo(document("create-qna",
                        links(
                                linkWithRel("self").description("생성된 qna 조회"),
                                linkWithRel("query-qna").description("qna 목록 조회"),
                                linkWithRel("update-qna").description("생성된 qna 수정"),
                                linkWithRel("profile").description("해당 Rest API profile 이동")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                        ),
                        requestFields(
                                fieldWithPath("title").description("생성할 qna 제목"),
                                fieldWithPath("content").description("생성할 qna 내용"),
                                fieldWithPath("tempSaveStatus").description("생성할 qna의 임시저장 여부"),
                                fieldWithPath("memberId").description("생성할 qna 작성자 Id"),
                                fieldWithPath("dtype").description("생성할 qna의 타입(블로그/Q&A)"),
                                fieldWithPath("thumbnailPath").description("생성할 qna의 썸네일 경로(옵션)")
                                
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json"),
                                headerWithName(HttpHeaders.LOCATION).description("응답 Location")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("생성된 qna의 식별자"),
                                fieldWithPath("title").description("생성된 qna의 제목"),
                                fieldWithPath("content").description("생성된 qna의 내용"),
                                fieldWithPath("writerId").description("생성된 qna의 작성자 식별자"),
                                fieldWithPath("writerEmail").description("생성된 qna 작성자 이메일"),
                                fieldWithPath("writerName").description("생성된 qna의 작성자 이름"),
                                fieldWithPath("views").description("생성된 qna의 조회수"),
                                fieldWithPath("thumbnailPath").description("생성된 qna의 썸네일 경로"),
                                fieldWithPath("tempSaveStatus").description("생성된 qna의 임시저장여부"),
                                fieldWithPath("dtype").description("생성된 qna의 타입(블로그/Q&A)"),
                                fieldWithPath("_links.self").description("생성된 qna 조회 URI"),
                                fieldWithPath("_links.query-qna").description("qna 목록 조회"),
                                fieldWithPath("_links.update-qna").description(")생성된 qna 수정 URI"),
                                fieldWithPath("_links.profile").description("해당 Rest API profile 이동"),

                                fieldWithPath("createId").description("qna를 생성한 멤버 Id"),
                                fieldWithPath("createDateTime").description("qna를 생성한 시간"),
                                fieldWithPath("modifyId").description("qna를 마지막으로 수정한 멤버 Id"),
                                fieldWithPath("modifyDateTime").description("qna를 마지막으로 수정한 시간")
                        )
                ))
                ;
    }

    @Test
    @DisplayName("Qna 생성 - 해시태그 포함")
    @Disabled
    public void createQnaWithHashTag(){

    }



    public Member getRandomMember(){
        Integer ints = new Random().nextInt(6);
        String email = "zee12"+ ints +"@gstim.com";
        String password = "itm@6700";

        Member member = Member.builder()
                .email(email)
                .password(password)
                .build();

        this.memberRepository.save(member);

        return member;
    }

}