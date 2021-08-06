package com.blogcode.api.qna;

import com.blogcode.api.BlogRestController;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.HashTagRepository;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.blogcode.validator.QnaValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/qna", produces = MediaTypes.HAL_JSON_VALUE)
public class QnaRestController {

    private final QnaService qnaService;

    private final QnaRepository qnaRepository;

    private final QnaValidator qnaValidator;

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final HashTagRepository hashTagRepository;

    @Value("${msg.front-url}")
    private String frontURI;

    // TODO qna 목록 조회
    @GetMapping
    public ResponseEntity queryQna(@RequestParam(required = false) String interval,
                                   @RequestParam(required = false) String search,
                                   PagedResourcesAssembler<Posts> assembler,
                                   Pageable pageable){
        // TODO 서비스 하나 만들어서 조건에 따른 qna 리스트 목록 조회하기(trend,recent,search등등
        String itv = "?interval=";

        Page<Posts> page = this.qnaRepository.findAll(pageable);
        var resQna = assembler.toModel(page);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(QnaRestController.class);
        WebMvcLinkBuilder self = selfLinkBuilder;
        WebMvcLinkBuilder trend = selfLinkBuilder;
        WebMvcLinkBuilder trendWeek = selfLinkBuilder.slash("?interval=day");
        WebMvcLinkBuilder trendMonth = selfLinkBuilder.slash("?interval=week");
        WebMvcLinkBuilder trendYear = selfLinkBuilder.slash("?interval=year");
        WebMvcLinkBuilder recent = selfLinkBuilder.slash("?interval=recent");
        WebMvcLinkBuilder searchLink = selfLinkBuilder.slash("?search={keyword}");
        WebMvcLinkBuilder blogLink = linkTo(BlogRestController.class);
        Link profile = Link.of("/docs/qna.html#resources-get-qna-list");


        resQna.add(linkTo(QnaRestController.class).slash("?interval=day").withRel("trend"));
        resQna.add(linkTo(QnaRestController.class).slash("?interval=week").withRel("trend-week"));
        resQna.add(linkTo(QnaRestController.class).slash("?interval=month").withRel("trend-month"));
        resQna.add(linkTo(QnaRestController.class).slash("?interval=year").withRel("trend-year"));
        resQna.add(linkTo(QnaRestController.class).slash("?interval=recent").withRel("recent"));
        resQna.add(linkTo(QnaRestController.class).slash("?search={keyword}").withRel("search"));
        resQna.add(linkTo(BlogRestController.class).withRel("blog"));
        resQna.add(Link.of("/docs/qna.html#resources-get-qna-list").withRel("profile"));

        return ResponseEntity.ok(resQna);
//        linkWithRel("self").description("생성된 qna 조회"),
//                linkWithRel("trend").description("하루 중 가장 인기있는 순으로 qna 조회"),
//                linkWithRel("trend-week").description("일주일 중 가장 인기있는 순으로 qna 조회"),
//                linkWithRel("trend-month").description("한달 중 가장 인기있는 순으로 qna 조회"),
//                linkWithRel("trend-year").description("일년 중 가장 인기있는 순으로 qna 조회"),
//                linkWithRel("recent").description("최신 순으로 qna 조회"),
//                linkWithRel("search").description("검색 키워드로 qna 조회"),
//                linkWithRel("blog").description("blog 목록 조회"),
//                linkWithRel("profile").description("해당 Rest API profile 이동")
    }


    // TODO qna 조회
    @GetMapping("/{id}")
    public ResponseEntity getQna(@PathVariable Long id){


        // TODO qna 조회수

        return ResponseEntity.ok().build();
    }
    // TODO qna 생성
    @PostMapping
    public ResponseEntity createQna(@RequestBody @Valid QnaDTO qnaDTO, Errors errors){

        if(errors.hasErrors()){
            return badRequest(errors);
        }

        qnaValidator.validate(qnaDTO, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        Posts qna = this.modelMapper.map(qnaDTO, Posts.class);
        Posts posts = this.qnaRepository.save(qna);
        Long id = posts.getId();
        Long memberId = qnaDTO.getMemberId();

        Member member = this.memberRepository.findById(memberId).orElseThrow(()-> new NoSuchElementException());
        posts.setMemberData(member);

        this.hashTagRepository.saveAll(posts.getHashTags());

        WebMvcLinkBuilder selfLinkBuilder = linkTo(QnaRestController.class);
        URI createUri = selfLinkBuilder.slash(id).toUri();

        EntityModel<Posts> resQna = EntityModel.of(posts);
        resQna.add(selfLinkBuilder.slash(id).withSelfRel());
        resQna.add(selfLinkBuilder.withRel("query-qna"));
        resQna.add(selfLinkBuilder.slash(id).withRel("update-qna"));
        resQna.add(Link.of("/docs/qna.html#resources-qna-create").withRel("profile"));

        return ResponseEntity.created(createUri).body(resQna);
    }

    // TODO qna 수정
    @PutMapping("/{id}")
    public ResponseEntity updateQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO qna 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO qna 좋아요
    @PostMapping("/like/{id}")
    public ResponseEntity likeQna(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO qna 조회수
    public void updatedViews(HttpRequest request){

    }

    // TODO Bad Request 
    public ResponseEntity badRequest(Errors errors){

        EntityModel<Errors> resErrors = EntityModel.of(errors);
        resErrors.add(Link.of(frontURI).withRel("index"));
        return ResponseEntity.badRequest().body(resErrors);
    }


}
