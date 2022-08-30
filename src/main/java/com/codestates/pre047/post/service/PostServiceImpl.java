package com.codestates.pre047.post.service;

import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

        Post findPost = findVerifiedPost(post.getPostId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> findPost.setContent(content));

        return postRepository.save(findPost);
    }

    @Override
    public Post findPost(Long postId) {
        return findVerifiedPost(postId);
    }

    @Override
    public Page<Post> findPosts(int page, int size) {

        return postRepository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));

    }

    @Override
    public void deletePost(Long postId) {

        Post findPost = findVerifiedPost(postId);

        postRepository.delete(findPost);

    }
/*    private Post existPost(Long postId) {
        Optional<Post> existPost = postRepository.findById(postId);

        return existPost.orElseThrow(() ->
                new RuntimeException("PostId not exist"));
    }*/

    private Post findVerifiedPost(long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow(() -> new RuntimeException("PostId not exist"));

        return findPost;
    }
}
