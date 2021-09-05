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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;

import java.net.URI;
import java.util.List;

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
    @GetMapping("/{postId}")
    public ResponseEntity queryAnswer(@PathVariable Long postId){

        List<Reply> AnswerList = this.answerService.findAnswerList(postId);

        WebMvcLinkBuilder self = linkTo(AnswerController.class);

        CollectionModel<Reply> resAns = CollectionModel.of(AnswerList);
        resAns.add(self.slash(postId).withSelfRel());
        resAns.add(Link.of("/docs/answer.html#query-answer").withRel("profile"));

        return ResponseEntity.ok(resAns);
    }

    // TODO answer 생성
    @PostMapping
    public ResponseEntity createAnswer(@RequestBody @Valid AnswerDTO answerDTO, Errors errors){

        if(errors.hasErrors()){
            return badRequest(errors);
        }

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
        resAns.add(Link.of("/docs/answer.html#create-answer").withRel("profile"));

        return ResponseEntity.ok(resAns);
    }

    // TODO answer 수정
    @PutMapping("/{id}")
    public ResponseEntity updateAnswer(@PathVariable Long id, @RequestBody @Valid AnswerDTO answerDTO, Errors errors){

        Reply reply = this.answerService.updateAnswer(id, answerDTO);

        WebMvcLinkBuilder self = linkTo(AnswerController.class);

        EntityModel<Reply> resRpl = EntityModel.of(reply);
        resRpl.add(self.slash(id).withSelfRel());
        resRpl.add(Link.of("/docs/answer.html#update-answer").withRel("profile"));

        return ResponseEntity.ok(resRpl);
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
