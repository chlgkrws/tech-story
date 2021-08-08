package com.blogcode.api.qna;

import com.blogcode.InternalApiApplication;
import com.blogcode.common.RestDocsConfiguration;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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

import java.util.List;
import java.util.Random;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = InternalApiApplication.class)
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
                .hashTag(List.of("박요한","최학준"))
                .build();

        this.mockMvc.perform(post("/api/qna")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
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
                                fieldWithPath("thumbnailPath").description("생성할 qna의 썸네일 경로(옵션)"),
                                fieldWithPath("hashTag").description("생성할 qna의 해시태그들 (옵션/List)")
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
    @DisplayName("Qna 생성 실패 - 맴버 없음")
    public void qnaCreateFail_member() throws Exception{
        QnaDTO qnaDTO = QnaDTO.builder()
                .title("테스트 글")
                .content("테스트")
                .thumbnailPath("/")
                .tempSaveStatus("N")
                .dType(PostType.QNA)
                .build();

        this.mockMvc.perform(post("/api/qna")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(qnaDTO))
        )
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

    @Test
    @DisplayName("Qna 생성 실패 - 빈 객체 전송")
    public void qnaCreateFail_All() throws Exception{
        QnaDTO qnaDTO = QnaDTO.builder()
                .build();

        this.mockMvc.perform(post("/api/qna")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(qnaDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors.[0].field").exists())
                .andExpect(jsonPath("errors.[1].field").exists())
                .andExpect(jsonPath("errors.[2].field").exists())
                .andExpect(jsonPath("errors.[3].field").exists())
                .andExpect(jsonPath("errors.[4].field").exists())
                .andExpect(jsonPath("_links.index").exists())
                .andDo(print())
        ;
    }

    @Test
    @DisplayName("Qna 목록 조회 - 조건 X")
    public void getQnaList() throws Exception {
        this.mockMvc.perform(get("/api/qna")
                .param("page", "0")
                .param("size","6")
                .param("sort","createDateTime,DESC")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-qna-list",
                        links(
                                linkWithRel("self").description("생성된 qna 조회"),
                                linkWithRel("trend").description("하루 중 가장 인기있는 순으로 qna 조회"),
                                linkWithRel("trend-week").description("일주일 중 가장 인기있는 순으로 qna 조회"),
                                linkWithRel("trend-month").description("한달 중 가장 인기있는 순으로 qna 조회"),
                                linkWithRel("trend-year").description("일년 중 가장 인기있는 순으로 qna 조회"),
                                linkWithRel("recent").description("최신 순으로 qna 조회"),
                                linkWithRel("search").description("검색 키워드로 qna 조회"),
                                linkWithRel("blog").description("blog 목록 조회"),
                                linkWithRel("profile").description("해당 Rest API profile 이동")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                        ),
                        requestParameters(
                                parameterWithName("page").description("qna를 조회할 페이지 단위"),
                                parameterWithName("size").description("한번에 qna를 조회할 페이지 개수"),
                                parameterWithName("sort").description("정렬할 기준")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json")
                        ),
                        relaxedResponseFields(

                        )

                ));
        ;
    }

    @Test
    @DisplayName("Qna 조회")
    @Disabled
    public void getQnaDetail() throws Exception {
        this.mockMvc.perform(get("/api/qna")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
        )
                .andExpect(status().isBadRequest())

        ;
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

    public QnaDTO stackRandomPosts(){
        Member randomMember = getRandomMember();
        Integer ints = new Random().nextInt(6);

        QnaDTO qnaDTO = QnaDTO.builder()
                .title("테스트 글" + ints)
                .content("테스트" + ints)
                .thumbnailPath("/" + ints)
                .tempSaveStatus("N" + ints)
                .dType(PostType.QNA)
                .memberId(randomMember.getId())
                .hashTag(List.of("박요한", "최학준"))
                .build();
        return qnaDTO;
    }

}