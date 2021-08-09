package com.blogcode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * com.blogcode.blog
 *
 * Description :
 * </pre>
 *
 * @author leejinho
 * @version 1.0
 * @see <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일     수정자   수정내용
 * ---------- -------- -------------------
 * 2021.07.07 leejinho 최초 생성
 * </pre>
 * @since 2021.07.07
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    @GetMapping
    public String writeBlogView() {
        return "blog/blog-write";
    }
}
