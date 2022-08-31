package com.codestates.pre047.post.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotBlank(message = "Title is missing.")
        private String title;

        @NotBlank(message = "Body is missing.")
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        private long postId;

        @NotBlank(message = "Title is missing.")
        private String title;

        @NotBlank(message = "Body is missing.")
        private String content;

    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private long postId;
        private String title;
        private String  content;

        public Response(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}
