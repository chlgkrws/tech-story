package com.blogcode.posts.service;

import com.blogcode.member.service.MemberService;
import com.blogcode.posts.domain.Reply;
import com.blogcode.posts.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final MemberService memberService;

    private final PostsService postsService;

    public Reply saveAnswer(Reply reply) {


        return this.answerRepository.save(reply);
    }
}
