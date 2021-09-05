package com.blogcode.api.qna;

import com.blogcode.member.service.MemberService;
import com.blogcode.posts.domain.Reply;
import com.blogcode.posts.dto.AnswerDTO;
import com.blogcode.posts.service.AnswerService;
import com.blogcode.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/answer", produces = MediaTypes.HAL_JSON_VALUE)
public class AnswerController {

    private final PostsService postsService;

    private final MemberService memberService;

    private final AnswerService answerService;

    private final ModelMapper modelMapper;

    @Value("${msg.front-url}")
    private String frontURI;

    // TODO answer 조회
    @GetMapping("/{id}")
    public ResponseEntity queryAnswer(@PathVariable Long postId){

        return ResponseEntity.ok().build();
    }

    // TODO answer 생성
    @PostMapping
    public ResponseEntity createAnswer(@RequestBody @Valid AnswerDTO answerDTO, Errors errors){

        if(errors.hasErrors()){
            return badRequest(errors);
        }

        this.modelMapper.addMappings(new PropertyMap<AnswerDTO, Reply>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        Reply reply = this.modelMapper.map(answerDTO, Reply.class);
        reply.setMember(this.memberService.findById(answerDTO.getMemberId()));
        reply.setPosts(this.postsService.findById(answerDTO.getPostId()));

        Reply savedReply = this.answerService.saveAnswer(reply);
        Long id = savedReply.getId();

        WebMvcLinkBuilder self = linkTo(AnswerController.class);

        EntityModel<Reply> resAns = EntityModel.of(savedReply);
        resAns.add(self.slash(id).withSelfRel());
        resAns.add(self.slash(id).withRel("update-answer"));
        resAns.add(self.withRel("query-answer"));
        resAns.add(Link.of("/docs/answer.html/create-answer").withRel("profile"));

        return ResponseEntity.ok(resAns);
    }

    // TODO answer 수정
    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO answer 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id){

        return ResponseEntity.ok().build();
    }

    // TODO Bad Request
    public ResponseEntity badRequest(Errors errors){

        EntityModel<Errors> resErrors = EntityModel.of(errors);
        resErrors.add(Link.of(frontURI).withRel("index"));
        return ResponseEntity.badRequest().body(resErrors);
    }
}
