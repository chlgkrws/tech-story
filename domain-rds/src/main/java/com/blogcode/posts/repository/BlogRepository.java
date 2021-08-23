package com.blogcode.posts.repository;

import com.blogcode.posts.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findAllByTitleContainingOrContentContainingOrHashTagsContaining(String search, Pageable pageable);
}
