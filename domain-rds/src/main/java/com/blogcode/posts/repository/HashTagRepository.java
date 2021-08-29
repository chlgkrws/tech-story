package com.blogcode.posts.repository;

import com.blogcode.posts.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    public List<HashTag> findByPostsId(Long id);
}
