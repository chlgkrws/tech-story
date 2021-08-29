package com.blogcode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/qna")
public class QnaController {

    // TODO Qna 작성 페이지
    @GetMapping("/write")
    public ModelAndView createQna(ModelAndView modelAndView, HttpServletRequest request){

        return modelAndView;
    }

    // TODO Qna detail
    @GetMapping("/{id}")
    public ModelAndView getQna(ModelAndView modelAndView, @PathVariable Long id, HttpServletRequest request){

        return modelAndView;
    }


    @PutMapping("/{id}")
    public ModelAndView updateQna(ModelAndView modelAndView, @PathVariable Long id, HttpServletRequest request){

        return modelAndView;
    }

}
