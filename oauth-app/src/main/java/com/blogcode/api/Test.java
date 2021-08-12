package com.blogcode.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/bearer")
public class Test {

    @GetMapping("/root")
    public String testController(){
        System.out.println("@@@@@@@@@@@@");
        return "hi";
    }

    @GetMapping("test")
    public String testController2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("authorities = " + authorities);
        System.out.println("!!!!!!!!!!");
        return "hi";
    }
}
