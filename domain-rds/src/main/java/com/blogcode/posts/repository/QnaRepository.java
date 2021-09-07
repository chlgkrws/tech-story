package com.blogcode.posts.repository;

import com.blogcode.posts.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QnaRepository extends JpaRepository<Posts, Long> {

    @Modifying
    @Query("update Posts p set views = views + 1 where posts_id = :id")
    public void increaseOneViews(@Param("id")Long id);
}
