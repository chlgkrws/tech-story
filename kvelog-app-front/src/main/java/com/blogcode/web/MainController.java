package com.blogcode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * k-velog
 *
 * Description :
 * </pre>
 *
 * @author LeeJH
 * @since 2021-06-26
 */
@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String main() {
        return "main";
    }
}
