package com.blogcode.web;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/qna")
public class QnaController {

    @GetMapping
    public int getQnaList(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(3000);
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .build();
        httpRequestFactory.setHttpClient(httpClient);

        org.springframework.web.client.RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("Content-Type","application/hal+json");
        HttpEntity entity = new HttpEntity<>(httpHeaders);

        String url = "http://localhost:8084/api/qna";

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println(exchange);
        return 1;
    }

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
