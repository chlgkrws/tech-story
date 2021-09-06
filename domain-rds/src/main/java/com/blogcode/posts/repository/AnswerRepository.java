package com.blogcode.posts.repository;

import com.blogcode.posts.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPostsId(Long id);

    @Modifying
    @Query("update Reply r set r.useYn = 0 where r.id = :id")
    void deleteAnswer(@Param("id") Long id);
}
