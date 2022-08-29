package com.codestates.pre047.post.controller;

import com.codestates.pre047.post.dto.PostPostDto;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.mapper.PostMapper;
import com.codestates.pre047.response.ErrorResponse;
import com.codestates.pre047.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/posts")
@Validated
public class PostController {

    // mapper 자동화 / 예외처리 Refactoring / h2 -> my sql 연동

    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    // 글작성
    @PostMapping("/create")
    public ResponseEntity createPost(@Valid @RequestBody PostPostDto postPostDto) {

        /* mapper 자동화 구현 // Build.gradle 수정
        */

        Post post = mapper.postPostDtoToPost(postPostDto);

        Post response = postService.createPost(post);

        return new ResponseEntity<>(mapper.postToPostResponseDto(response), HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException e) {

        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResponse.FieldError> errors =
                fieldErrors.stream()
                        .map(error -> new ErrorResponse.FieldError(
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    } // 예외처리 일괄 수정 예정

    // 글수정

    @PatchMapping("/patch")
    public ResponseEntity patchPost() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 특정 게시글 조회

    @GetMapping("/{post-id}")
    public ResponseEntity findPost() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 글목록 조회

    @GetMapping
    public ResponseEntity findPosts() {
        return new ResponseEntity(HttpStatus.OK);
    }

    // 글 삭제

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}