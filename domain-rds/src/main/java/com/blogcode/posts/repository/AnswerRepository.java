package com.blogcode.posts.repository;

import com.blogcode.posts.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPostsId(Long id);
}
