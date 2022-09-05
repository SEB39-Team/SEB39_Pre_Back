package com.codestates.pre047.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AnswerDto {

    @Getter
    public static class Post {

        @NotBlank(message = "Missing Body")
        private String content;
    }

    @Getter
    public static class Patch {
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String content;
    }



}
