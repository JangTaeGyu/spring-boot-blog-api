package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaPostRepository extends Repository<Post, Long> {
    Post save(Post post);

    @Query(value = "select p from Post p where p.id = :id and p.deletedAt is null")
    Optional<Post> findById(Long id);
}
