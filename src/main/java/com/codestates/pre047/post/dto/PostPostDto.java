package com.codestates.pre047.post.dto;

import javax.validation.constraints.NotBlank;

public class PostPostDto {

    @NotBlank(message = "Title is missing.")
    private String title;
    @NotBlank(message = "Body is missing")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
