package com.codestates.pre047.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPatchDto {

    @Positive
    private Long postId;

    @NotBlank(message = "Title is missing.")
    private String title;

    @NotBlank(message = "Body is missing")
    private String content;
}
