package com.blogcode.api.qna;

import com.blogcode.InternalApiApplication;
import com.blogcode.api.SpringBaseTest;
import com.blogcode.common.RestDocsConfiguration;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.dto.AnswerDTO;
import com.blogcode.posts.dto.HashTagDTO;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = InternalApiApplication.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
class AnswerControllerTest extends SpringBaseTest {

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
    @DisplayName("Answer 생성")
    public void createQna() throws Exception {
        // 멤버 생성
        Member member = getRandomMember();

        // posts 생성
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
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(qnaDTO))
        );

        // answer 생성
        AnswerDTO answerDTO = AnswerDTO.builder()
                .upperId(null)
                .depth(null)
                .title("테스트 댓글")
                .content("테스트 컨텐트")
                .writerEmail("cgw981@naver.com")
                .likes(null)
                .postId(1L)
                .memberId(member.getId())
                .build();

        this.mockMvc.perform(post("/api/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(answerDTO))
        )
            .andDo(print())
            .andDo(document("create-answer",
                    links(
                            linkWithRel("self").description("생성된 answer 조회"),
                            linkWithRel("query-answer").description("answer 목록 조회"),
                            linkWithRel("update-answer").description("생성된 answer 수정"),
                            linkWithRel("profile").description("해당 Rest API profile 이동")
                    ),
                    requestHeaders(
                            headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                            headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                    ),
                    relaxedRequestFields(
                            fieldWithPath("upperId").description("생성할 answer의 부모 식별자"),
                            fieldWithPath("depth").description("생성할 answer의 계층"),
                            fieldWithPath("title").description("생성할 answer 제목"),
                            fieldWithPath("content").description("생성할 answer 내용"),
                            fieldWithPath("writerEmail").description("생성할 answer 작성자 Id"),
                            fieldWithPath("postId").description("생성할 answer의 게시물 Id"),
                            fieldWithPath("memberId").description("생성할 answer 작성자 Id")
                    ),
                    responseHeaders(
                            headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json")
                    ),
                    relaxedResponseFields(
                            fieldWithPath("id").description("생성된 answer의 식별자"),
                            fieldWithPath("upperId").description("생성된 answer의 부모 식별자"),
                            fieldWithPath("depth").description("생성된 answer의 계층"),
                            fieldWithPath("title").description("생성된 answer의 제목"),
                            fieldWithPath("content").description("생성된 answer의 내용"),
                            fieldWithPath("writerEmail").description("생성된 answer 작성자 이메일"),
                            fieldWithPath("writerName").description("생성된 answer의 작성자 이름"),
                            fieldWithPath("likes").description("생성된 answer의 좋아요 수"),
                            fieldWithPath("_links.self").description("생성된 answer 조회 URI"),
                            fieldWithPath("_links.query-answer").description("answer 목록 조회"),
                            fieldWithPath("_links.update-answer").description(")생성된 answer 수정 URI"),
                            fieldWithPath("_links.profile").description("해당 Rest API profile 이동"),

                            fieldWithPath("createId").description("answer를 생성한 멤버 Id"),
                            fieldWithPath("createDateTime").description("answer를 생성한 시간"),
                            fieldWithPath("modifyId").description("answer를 마지막으로 수정한 멤버 Id"),
                            fieldWithPath("modifyDateTime").description("answer를 마지막으로 수정한 시간")
                    )
                    ))
        ;
    }

    @Test
    @DisplayName("Answer - 조회")
    public void queryAnswer() {
        this.mockMvc.perform(post("/api/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(answerDTO))
        )
                .andDo(print())
                .andDo(document("create-answer",
                        links(
                                linkWithRel("self").description("생성된 answer 조회"),
                                linkWithRel("query-answer").description("answer 목록 조회"),
                                linkWithRel("update-answer").description("생성된 answer 수정"),
                                linkWithRel("profile").description("해당 Rest API profile 이동")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                        ),
                        relaxedRequestFields(
                                fieldWithPath("upperId").description("생성할 answer의 부모 식별자"),
                                fieldWithPath("depth").description("생성할 answer의 계층"),
                                fieldWithPath("title").description("생성할 answer 제목"),
                                fieldWithPath("content").description("생성할 answer 내용"),
                                fieldWithPath("writerEmail").description("생성할 answer 작성자 Id"),
                                fieldWithPath("postId").description("생성할 answer의 게시물 Id"),
                                fieldWithPath("memberId").description("생성할 answer 작성자 Id")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("생성된 answer의 식별자"),
                                fieldWithPath("upperId").description("생성된 answer의 부모 식별자"),
                                fieldWithPath("depth").description("생성된 answer의 계층"),
                                fieldWithPath("title").description("생성된 answer의 제목"),
                                fieldWithPath("content").description("생성된 answer의 내용"),
                                fieldWithPath("writerEmail").description("생성된 answer 작성자 이메일"),
                                fieldWithPath("writerName").description("생성된 answer의 작성자 이름"),
                                fieldWithPath("likes").description("생성된 answer의 좋아요 수"),
                                fieldWithPath("_links.self").description("생성된 answer 조회 URI"),
                                fieldWithPath("_links.query-answer").description("answer 목록 조회"),
                                fieldWithPath("_links.update-answer").description(")생성된 answer 수정 URI"),
                                fieldWithPath("_links.profile").description("해당 Rest API profile 이동"),

                                fieldWithPath("createId").description("answer를 생성한 멤버 Id"),
                                fieldWithPath("createDateTime").description("answer를 생성한 시간"),
                                fieldWithPath("modifyId").description("answer를 마지막으로 수정한 멤버 Id"),
                                fieldWithPath("modifyDateTime").description("answer를 마지막으로 수정한 시간")
                        )
                ))
        ;
    }
}