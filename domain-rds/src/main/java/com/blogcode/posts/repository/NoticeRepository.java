package com.blogcode.posts.repository;

import com.blogcode.posts.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Posts, Long> {
}
