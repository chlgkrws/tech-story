package com.blogcode.rest;

import com.blogcode.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * k-velog
 *
 * Description :
 * </pre>
 *
 * @author LeeJH
 * @since 2021-07-05
 */
@RestController
@RequestMapping("member")
public class MemberRestController {
    @GetMapping
    public Member test() {
        return new Member("test", "test@gsitm.com");
    }
}
