package com.codestates.pre047.post.service;

import com.codestates.pre047.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface PostService {


    // 게시글 작성
    Post createPost(Post post);

    // 게시글 수정

    Post updatePost(Post post);

    // 게시글 조회

    Post findPost(long postId);

    // 전체글 조회

    Page<Post> findPosts(int page, int size);

    // 전체글 삭제

    void deletePost(long postId);
}
