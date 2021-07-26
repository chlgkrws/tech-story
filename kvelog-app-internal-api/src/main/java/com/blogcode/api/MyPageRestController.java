package com.blogcode.api;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/mypage", produces = MediaTypes.HAL_JSON_VALUE)
public class MyPageRestController {

    // TODO 자기소개 조회/등록/수정
    // TODO 이미지 등록/수정/삭제
    // TODO 닉네임 변경 / 비밀번호 변경
    // TODO 블로그이름 조회/수정
    // TODO 개인정보 조회
    // TODO 이메일 수신설정 조회/수정
    // TODO 회원 탈퇴
}
