package com.blogcode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("/qna")
public class QnaController {

    // TODO Qna 작성 페이지
    @GetMapping("/write")
    public ModelAndView createQna(ModelAndView modelAndView, HttpServletRequest request){

        return modelAndView;
    }

    // TODO Qna detail
    @GetMapping
    public ModelAndView getQna(ModelAndView modelAndView, HttpServletRequest request){

        return modelAndView;
    }
}
