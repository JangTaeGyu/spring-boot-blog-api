package com.example.springbootblogapi.infrastructure;

import com.example.springbootblogapi.domain.post.Post;
import com.example.springbootblogapi.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private JpaPostRepository postRepository;

    @Override
    public Long create(Post post) {
        return postRepository.save(post).getId();
    }
}
