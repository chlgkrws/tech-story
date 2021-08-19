package com.blogcode.api.qna;

import com.blogcode.InternalApiApplication;
import com.blogcode.common.RestDocsConfiguration;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.PostType;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Random;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = InternalApiApplication.class)
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
class QnaRestControllerTest {

    @Autowired
    WebApplicationContext context;

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

 /*   @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation).uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8084))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }*/

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
        String postsList = "_embedded.postsList";

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
                                linkWithRel("profile").description("해당 Rest API profile 이동"),
                                linkWithRel("first").description("생성된 qna 중 가장 첫 번째 페이지"),
                                linkWithRel("last").description("생성된 qna 중 가장 마지막 페이지"),
                                //linkWithRel("prev").description("이전 qna 리스트 조회"),
                                linkWithRel("next").description("다음 qna 리스트 조회")
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
                                fieldWithPath(postsList).description("조회한 qna 리스트"),
                                fieldWithPath(postsList+"[0].createId").description("qna를 생성한 멤버 식별자"),
                                fieldWithPath(postsList+"[0].createDateTime").description("qna 생성 시간"),
                                fieldWithPath(postsList+"[0].id").description("조회한 qna 식별자"),
                                fieldWithPath(postsList+"[0].title").description("조회한 qna 제목"),
                                fieldWithPath(postsList+"[0].content").description("조회한 qna 내용"),
                                fieldWithPath(postsList+"[0].writerName").description("조회한 qna 작성자 닉네임"),
                                fieldWithPath(postsList+"[0].writerEmail").description("조회한 qna 작성자 이메일"),
                                fieldWithPath(postsList+"[0].views").description("조회한 qna 조회수"),
                                fieldWithPath(postsList+"[0].likes").description("조회한 qna 좋아요 수"),
                                fieldWithPath(postsList+"[0].thumbnailPath").description("조회한 qna 섬네일 링크"),
                                fieldWithPath(postsList+"[0].countScripting").description("조회한 qna이 스크립된 개수")
                        )

                ));
    }


    @Test
    @DisplayName("Qna 조회")
    public void getQnaDetail() throws Exception {
        this.mockMvc.perform(get("/api/qna/1")
                .accept(MediaTypes.HAL_JSON_VALUE)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("useYn").isNotEmpty())
                .andExpect(jsonPath("createDateTime").exists())
                .andExpect(jsonPath("writerName").exists())
                .andDo(print())
                .andDo(document("get-qna-detail",
                        links(
                                linkWithRel("self").description("해당 qna 조회"),
                                linkWithRel("update-qna").description("해당 qna 수정"),
                                linkWithRel("delete-qna").description("해당 qna 삭제"),
                                linkWithRel("likes").description("해당 qna 좋아요 링크"),
                                linkWithRel("scripting").description("해당 qna 스크립트 링크"),
                                linkWithRel("hashTag").description("해당 해시태그 화면 이동"),
                                linkWithRel("profile").description("해당 Rest API profile 이동"),
                                linkWithRel("create-reply").description("댓글 post 링크"),
                                linkWithRel("create-re-reply").description("대댓글 post 링크"),
                                linkWithRel("like-reply").description("댓글 좋아요 링크")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept 헤더")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("조회한 qna id"),
                                fieldWithPath("writerName").description("qna 작성자 name"),
                                fieldWithPath("writerId").description("qna 작성자 id"),
                                fieldWithPath("title").description("qna 제목"),
                                fieldWithPath("content").description("qna 내용"),
                                fieldWithPath("views").description("qna 조회수"),
                                fieldWithPath("likes").description("qna 좋아요 수"),
                                fieldWithPath("replyList").description("댓글 리스트"),
                                fieldWithPath("hashTags").description("해시태그 리스트"),
                                fieldWithPath("createDateTime").description("qna 생성시간")
                        )
                ));

        ;
    }


    @Test
    @DisplayName("Qna 목록 조회 - trend")
    public void getQnaList_trend() throws Exception {
        String postsList = "_embedded.postsList";


        this.mockMvc.perform(get("/api/qna")
                .param("page", "0")
                .param("size","6")
                .param("sort","createDateTime,DESC")
                .param("interval","day")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .header("X-Forwarded-Proto", "http")
                .header("X-Forwarded-Host","localhost")
                .header("X-Forwarded-Port", "8084")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath(postsList+"[0].createId").exists())
                .andExpect(jsonPath(postsList+"[0].createDateTime").exists())
                .andExpect(jsonPath(postsList+"[0].id").exists())
                .andExpect(jsonPath(postsList+"[0].title").exists())
                .andExpect(jsonPath(postsList+"[0].content").exists())
                .andExpect(jsonPath(postsList+"[0].writerName").exists())
                .andExpect(jsonPath(postsList+"[0].writerEmail").exists())
                .andExpect(jsonPath(postsList+"[0].views").exists())
                .andExpect(jsonPath(postsList+"[0].likes").exists())
                .andExpect(jsonPath(postsList+"[0].thumbnailPath").exists())
                .andExpect(jsonPath(postsList+"[0].countScripting").exists())
                .andDo(print())
                ;

    }

    @Test
    @DisplayName("Qna 목록 조회 - trend/recent/search")
    public void getQnaList_TODO() throws Exception {

    }


    @Test
    @DisplayName("Qna 수정 - 성공")
    public void qnaUpdate_OK() throws Exception {
        QnaDTO qnaDTO = QnaDTO.builder()
                .title("테스트 글 - 수정")
                .content("테스트 - 수정")
                .thumbnailPath("/ - 수정")
                .tempSaveStatus("N - 수정")
                .memberId(1L)
                .dType(PostType.QNA)
                .hashTag(List.of("박요한","최학준"))
                .build();

        this.mockMvc.perform(put("/api/qna/1")
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
                .andDo(document("update-qna",
                        links(
                                linkWithRel("self").description("생성된 qna 조회"),
                                linkWithRel("query-qna").description("qna 목록 조회"),
                                linkWithRel("profile").description("해당 Rest API profile 이동")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept 헤더"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 헤더")
                        ),
                        requestFields(
                                fieldWithPath("title").description("수정할 qna 제목"),
                                fieldWithPath("content").description("수정할 qna 내용"),
                                fieldWithPath("tempSaveStatus").description("수정할 qna의 임시저장 여부"),
                                fieldWithPath("dtype").description("수정할 qna의 타입(블로그/Q&A)"),
                                fieldWithPath("thumbnailPath").description("수정할 qna의 썸네일 경로(옵션)"),
                                fieldWithPath("hashTag").description("수정할 qna의 해시태그들 (옵션/List)")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type - Hal Json"),
                                headerWithName(HttpHeaders.LOCATION).description("응답 Location")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("qna의 식별자"),
                                fieldWithPath("title").description("수정된 qna의 제목"),
                                fieldWithPath("content").description("수정된 qna의 내용"),
                                fieldWithPath("thumbnailPath").description("수정된 qna의 썸네일 경로"),
                                fieldWithPath("tempSaveStatus").description("수정된 qna의 임시저장여부"),
                                fieldWithPath("dtype").description("수정된 qna의 타입(블로그/Q&A)"),
                                fieldWithPath("_links.self").description("수정된 qna 조회 URI"),
                                fieldWithPath("_links.query-qna").description("qna 목록 조회"),
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
    @DisplayName("Qna 수정 - 실패")
    public void qnaUpdate_FAIL() {

    }



    public Member getRandomMember(){
        Integer ints = new Random().nextInt(100);
        String email = "zee12"+ ints +"@gstim.com";
        String password = "itm@6700";

        Member member = Member.builder()
                .email(email)
                .password(password)
                .name("이진호")
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