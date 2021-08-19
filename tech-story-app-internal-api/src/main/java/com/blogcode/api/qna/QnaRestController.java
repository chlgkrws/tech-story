package com.blogcode.api.qna;

import com.blogcode.api.blog.BlogRestController;
import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.QnaDTO;
import com.blogcode.posts.repository.HashTagRepository;
import com.blogcode.posts.repository.QnaRepository;
import com.blogcode.posts.service.QnaService;
import com.blogcode.validator.QnaValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
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

        Page<Posts> page = this.qnaService.findByPostList(interval, search, pageable);
        var resQna = assembler.toModel(page);

        WebMvcLinkBuilder self = linkTo(QnaRestController.class);

        resQna.add(self.slash("?interval=day").withRel("trend"));
        resQna.add(self.slash("?interval=week").withRel("trend-week"));
        resQna.add(self.slash("?interval=month").withRel("trend-month"));
        resQna.add(self.slash("?interval=year").withRel("trend-year"));
        resQna.add(self.slash("?interval=recent").withRel("recent"));
        resQna.add(self.slash("?search={keyword}").withRel("search"));
        resQna.add(linkTo(BlogRestController.class).withRel("blog"));
        resQna.add(Link.of("/docs/qna.html#resources-get-qna-list").withRel("profile"));

        return ResponseEntity.ok(resQna);
    }


    // TODO qna 조회
    @GetMapping("/{id}")
    public ResponseEntity getQna(@PathVariable Long id){

        Posts posts = this.qnaRepository.findById(id).orElseThrow(() -> new NoSuchElementException());


        // TODO qna 조회수

        WebMvcLinkBuilder self = linkTo(QnaRestController.class);
        EntityModel<Posts> resQna = EntityModel.of(posts);
        Long postId = posts.getId();

        resQna.add(self.slash(postId).withSelfRel());
        resQna.add(self.slash(postId).withRel("update"));

        return ResponseEntity.ok(resQna);
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

        WebMvcLinkBuilder self = linkTo(QnaRestController.class);
        URI createUri = self.slash(id).toUri();

        EntityModel<Posts> resQna = EntityModel.of(posts);
        resQna.add(self.slash(id).withSelfRel());
        resQna.add(self.withRel("query-qna"));
        resQna.add(self.slash(id).withRel("update-qna"));
        resQna.add(Link.of("/docs/qna.html#resources-qna-create").withRel("profile"));

        return ResponseEntity.created(createUri).body(resQna);
    }

    // TODO qna 수정
    @PutMapping("/{id}")
    public ResponseEntity updateQna(@PathVariable Long id, @RequestBody @Valid QnaDTO qnaDTO, Errors errors){

        if(errors.hasErrors()){
            return badRequest(errors);
        }

        qnaValidator.validate(qnaDTO, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }


        Posts posts = this.qnaRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        // TODO this.qnaService.qnaToEntity(qnaDTO, posts);


        WebMvcLinkBuilder self = linkTo(QnaRestController.class);

        EntityModel<Posts> resQna = EntityModel.of(posts);
        resQna.add(self.slash(id).withSelfRel());
        resQna.add(self.withRel("query-qna"));
        resQna.add(Link.of("/docs/qna.html/resources-qna-update").withRel("profile"));

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
