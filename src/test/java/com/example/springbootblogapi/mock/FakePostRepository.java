package com.example.springbootblogapi.mock;

import com.example.springbootblogapi.domain.post.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class FakePostRepository implements PostRepository {
    private final AtomicLong id = new AtomicLong(1L);
    private final List<Post> data = new ArrayList<>();

    public FakePostRepository() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        IntStream.range(1, 11).forEach(i -> {
            Long categoryId = random.nextLong(1L, 6L);

            Post post = Post.fakeEntity(id.getAndIncrement());
            post.update(new PostData(categoryId, "Post Title 0" + i, "Post Body 0" + i));
            this.data.add(post);
        });
    }

    @Override
    public Page<PostDto> searchBy(PostSearchData postSearchData, Pageable pageable) {
        return null;
    }

    @Override
    public Long create(Post post) {
        Post createdPost = Post.fakeEntity(id.getAndIncrement());
        return createdPost.getId();
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return data.stream().filter(post -> post.getId().equals(postId)).findFirst();
    }
}
