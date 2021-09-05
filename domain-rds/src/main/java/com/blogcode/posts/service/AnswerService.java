package com.blogcode.posts.service;

import com.blogcode.member.service.MemberService;
import com.blogcode.posts.domain.Reply;
import com.blogcode.posts.dto.AnswerDTO;
import com.blogcode.posts.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public List<Reply> findAnswerList(Long postsId) {
        return this.answerRepository.findByPostsId(postsId);
    }

    public Reply updateAnswer(Long id, AnswerDTO answerDTO) {
        Reply reply = this.answerRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        reply.setTitle(answerDTO.getTitle());
        reply.setContent(answerDTO.getContent());

        return reply;
    }
}
