package com.codestates.pre047.post.controller;

import com.codestates.pre047.post.dto.PostDto;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.mapper.PostMapper;
import com.codestates.pre047.post.service.PostService;
import com.codestates.pre047.response.MultiResponseDto;
import com.codestates.pre047.response.SingleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@Validated
@Slf4j
public class PostController {

    //   예외처리 Refactoring / h2 -> my sql 연동


    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    // 글작성
    @PostMapping
    public ResponseEntity createPost(@Valid @RequestBody PostDto.Post requestBody) {
        Post post = mapper.postPostToPost(requestBody);

        Post createdPost = postService.createPost(post);

        PostDto.Response response = mapper.postToPostResponse(createdPost);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }



    // 글수정

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") @Positive long postId,
                                    @Valid @RequestBody PostDto.Patch requestBody) {

        requestBody.setPostId(postId);

        Post post = postService.updatePost(mapper.postPatchToPost(requestBody));

        PostDto.Response response = mapper.postToPostResponse(post);

        return new ResponseEntity<>( new SingleResponseDto<>(response),HttpStatus.OK);
    }

    // 특정 게시글 조회

    @GetMapping("/{post-id}")
    public ResponseEntity getCoffee(@PathVariable("post-id") @Positive long postId) {

        Post post = postService.findPost(postId);
        PostDto.Response response = mapper.postToPostResponse(post);

        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    // 전체 글목록 조회

    @GetMapping
    public ResponseEntity getCoffees(@RequestParam @Positive int page, @RequestParam @Positive int size) {

        Page<Post> pageposts = postService.findPosts(page-1, size);

        List<Post> posts = pageposts.getContent();

        List<PostDto.Response> responses = mapper.postsToPostResponses(posts);

        return new ResponseEntity<>(new MultiResponseDto<>(responses,
                pageposts),HttpStatus.OK);
    }

    // 글 삭제

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {

        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
