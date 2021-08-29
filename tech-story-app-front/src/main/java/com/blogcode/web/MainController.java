package com.blogcode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView main(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.setViewName("main");

        response.setHeader("Access-Control-Allow-Origin", "*");

        return modelAndView;
    }
}
