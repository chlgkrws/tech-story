package com.blogcode.api;

import com.blogcode.posts.domain.Posts;
import com.blogcode.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/blog", produces = MediaTypes.HAL_JSON_VALUE)
public class BlogRestController {

    private final PostsService postsService;

    // TODO blog 목록 조회
    @GetMapping
    public ResponseEntity queryBlogs(Posts posts){


        return ResponseEntity.ok().build();
    }

    // TODO blog 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity getBlog(@PathVariable Long id){


        return ResponseEntity.ok().build();
    }

    // TODO blog 생성
    @PostMapping
    public ResponseEntity createBlog(Posts posts){


        return ResponseEntity.ok().build();
    }

    // TODO blog 수정
    @PutMapping("/{id}")
    public ResponseEntity createBlog(@PathVariable Long id){


        return ResponseEntity.ok().build();
    }

    // TODO blog 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBlog(@PathVariable Long id){


        return ResponseEntity.ok().build();
    }
}
