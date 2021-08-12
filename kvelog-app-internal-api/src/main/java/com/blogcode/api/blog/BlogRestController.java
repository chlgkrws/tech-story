package com.blogcode.api.blog;

import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/blog", produces = MediaTypes.HAL_JSON_VALUE)
public class BlogRestController {

    private final PostsService postsService;

    // TODO blog 목록 조회
    @GetMapping
    public ResponseEntity<List<BlogDto>> queryBlogs() {

        return ResponseEntity.ok(postsService.findAllBlog());
    }

    // TODO blog 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable Long id){
        return ResponseEntity.ok(postsService.findBlogById(id));
    }

//    // TODO blog 생성
//    @PostMapping
//    public ResponseEntity createBlog(@RequestBody @Valid Posts posts, Errors errors){
//
//        if(errors.hasErrors()){
//            return badRequest(errors);
//        }
//
//        //postsValidator.validate(posts, errors);
//
//        if(errors.hasErrors()){
//            return badRequest(errors);
//        }
//
//        Posts savedPost = this.postsService.save(posts);
//        WebMvcLinkBuilder selfLinkBuilder = linkTo(BlogRestController.class).slash(savedPost.getId());
//        URI createUri = selfLinkBuilder.toUri();
//
//        EntityModel<Posts> postsResource = new EntityModel<>(posts);
//        postsResource.add(selfLinkBuilder.withSelfRel());
//        postsResource.add(linkTo(BlogRestController.class).withRel("query-blogs"));
//        postsResource.add(selfLinkBuilder.withRel("update-blog"));
//        postsResource.add(new Link("/docs/blog.html#resources-blog-create","profile"));
//
//        return ResponseEntity.created(createUri).body(postsResource);
//    }

    // TODO blog 생성
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody @Valid BlogDto blogDto) {
        return ResponseEntity.ok(postsService.saveByDto(blogDto));
    }

    // TODO blog 수정
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> modifyBlog(@RequestBody @Valid BlogDto blogDto){
        return ResponseEntity.ok(postsService.saveByDto(blogDto));
    }

    // TODO blog 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBlog(@PathVariable Long id){
        return ResponseEntity.ok(postsService.deleteBoard(id));
    }

//    // TODO index controller 추가 후 생성
//    private ResponseEntity badRequest(Errors errors){
//        EntityModel<Errors> errorsResource = new EntityModel<>(errors);
//        errorsResource.add(new Link(""));
//        return ResponseEntity.badRequest().body(errorsResource);
//    }
}
