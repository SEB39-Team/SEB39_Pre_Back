package com.codestates.pre047.post.service;

import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post findPost(Long postId) {
        return existPost(postId);
    }

    @Override
    public Page<Post> findPosts(int page, int size) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }


    private Post existPost(Long postId) {
        Optional<Post> existPost = postRepository.findById(postId);

        return existPost.orElseThrow(() ->
                new RuntimeException("PostId not exist"));
    }
}
