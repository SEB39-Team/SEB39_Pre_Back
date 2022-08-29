package com.codestates.pre047.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private long postId;

    private String title;

    private String content;
}
