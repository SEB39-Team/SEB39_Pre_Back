package com.codestates.pre047.post.controller;

import com.codestates.pre047.post.dto.PostPatchDto;
import com.codestates.pre047.post.dto.PostPostDto;
import com.codestates.pre047.post.dto.PostResponseDto;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.mapper.PostMapper;
import com.codestates.pre047.post.service.PostService;
import com.codestates.pre047.response.MultiResponseDto;
import com.codestates.pre047.response.SingleResponseDto;
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
public class PostController {

    // mapper 자동화 / 예외처리 Refactoring / h2 -> my sql 연동
    /* mapper 자동화 구현 // Build.gradle 수정
     */

    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    // 글작성
    @PostMapping("/create")
    public ResponseEntity createPost(@Valid @RequestBody PostPostDto postPostDto) {

        Post post = postService.createPost(mapper.postPostDtoToPost(postPostDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postToPostResponseDto(post)), HttpStatus.CREATED);
    }



    // 글수정

    @PatchMapping("/{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") @Positive long postId,
                                    @Valid @RequestBody PostPatchDto postPatchDto) {

        postPatchDto.setPostId(postId);


        Post post = postService.updatePost(mapper.postPatchDtoToPost(postPatchDto));

        return new ResponseEntity<>( new SingleResponseDto<>(mapper.postToPostResponseDto(post)),HttpStatus.OK);
    }

    // 특정 게시글 조회

    @GetMapping("/{post-id}")
    public ResponseEntity getCoffee(@PathVariable("post-id") @Positive long postId) {

        Post post = postService.findPost(postId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postToPostResponseDto(post)),HttpStatus.OK);
    }

    // 전체 글목록 조회

    @GetMapping
    public ResponseEntity getCoffees(@RequestParam @Positive int page, @RequestParam @Positive int size) {

        Page<Post> pageposts = postService.findPosts(page-1, size);

        List<Post> posts = pageposts.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToPostsDtoResponseDtos(posts),
                pageposts),HttpStatus.OK);
    }

    // 글 삭제

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") long postId) {

        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
