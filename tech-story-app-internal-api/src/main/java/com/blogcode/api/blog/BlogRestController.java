package com.blogcode.api.blog;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.dto.BlogDto;
import com.blogcode.posts.repository.BlogRepository;
import com.blogcode.posts.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/blog", produces = MediaTypes.HAL_JSON_VALUE)
public class BlogRestController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    // TODO blog 목록 조회
    @GetMapping
    public ResponseEntity queryBlogs(@RequestParam(required = false) String period,
                                     @RequestParam(required = false) String search,
                                     PagedResourcesAssembler<BlogDto> assembler,
                                     Pageable pageable) {

        Page<BlogDto> blogDtoPage = this.blogService.findAll(period, search, pageable);
        var resBlog = assembler.toModel(blogDtoPage);

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(BlogRestController.class);
        resBlog.add(webMvcLinkBuilder.slash("?interval=day").withRel("trend"));
        resBlog.add(webMvcLinkBuilder.slash("?interval=week").withRel("trend-week"));
        resBlog.add(webMvcLinkBuilder.slash("?interval=month").withRel("trend-month"));
        resBlog.add(webMvcLinkBuilder.slash("?interval=year").withRel("trend-year"));
        resBlog.add(webMvcLinkBuilder.slash("?interval=recent").withRel("recent"));
        resBlog.add(webMvcLinkBuilder.slash("?search={keyword}").withRel("search"));
        resBlog.add(linkTo(BlogRestController.class).withRel("blog"));
        resBlog.add(Link.of("/docs/qna.html#resources-get-qna-list").withRel("profile"));

        return ResponseEntity.ok().build();
    }

    // TODO blog 단일 조회

    @GetMapping("{id}")
    public ResponseEntity getBlog(@PathVariable Long id){
        return ResponseEntity.ok(blogService.findBlogById(id));
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
//        Posts savedPost = this.blogService.save(posts);
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
    public ResponseEntity createBlog(@RequestBody @Valid BlogDto blogDto) {
        BlogDto savedDto = blogService.saveByDto(blogDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(BlogRestController.class);
        URI uri = webMvcLinkBuilder.slash(savedDto.getId()).toUri();

        // 도메인 객체를 감싸고, 그에 링크를 추가하는 객체
        EntityModel<BlogDto> resBlog = EntityModel.of(savedDto);
        resBlog.add(webMvcLinkBuilder.slash(savedDto.getId()).withSelfRel());
        resBlog.add(webMvcLinkBuilder.withRel("query-blog"));
        resBlog.add(webMvcLinkBuilder.slash(blogDto.getId()).withRel("create-blog"));
        resBlog.add(Link.of("/docs/blog.html#resources-blog-create").withRel("profile"));

        return ResponseEntity.created(uri).body(resBlog);
    }

    // TODO blog 수정
    @PutMapping("{id}")
    public ResponseEntity modifyBlog(@RequestBody @Valid BlogDto blogDto){
        BlogDto updatedDto = blogService.saveByDto(blogDto);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(BlogRestController.class);
        URI uri = webMvcLinkBuilder.slash(updatedDto.getId()).toUri();

        // 도메인 객체를 감싸고, 그에 링크를 추가하는 객체
        EntityModel<BlogDto> resBlog = EntityModel.of(updatedDto);
        resBlog.add(webMvcLinkBuilder.slash(updatedDto.getId()).withSelfRel());
        resBlog.add(webMvcLinkBuilder.withRel("query-blog"));
        resBlog.add(webMvcLinkBuilder.slash(blogDto.getId()).withRel("update-blog"));
        resBlog.add(Link.of("/docs/blog.html#resources-blog-create").withRel("profile"));

        return ResponseEntity.created(uri).body(resBlog);
    }

    // TODO blog 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteBlog(@PathVariable Long id){
        return ResponseEntity.ok(blogService.deleteBoard(id));
    }

//    // TODO index controller 추가 후 생성
//    private ResponseEntity badRequest(Errors errors){
//        EntityModel<Errors> errorsResource = new EntityModel<>(errors);
//        errorsResource.add(new Link(""));
//        return ResponseEntity.badRequest().body(errorsResource);
//    }
}
