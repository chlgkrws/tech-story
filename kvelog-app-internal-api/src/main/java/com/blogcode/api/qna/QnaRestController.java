package com.blogcode.api.qna;

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
    public ResponseEntity queryQna(){
        System.out.println("frontURI = " + frontURI);
        return ResponseEntity.ok().build();
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

        Member member = this.memberRepository.findById(qnaDTO.getMemberId()).orElseThrow(()-> new NoSuchElementException());
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
