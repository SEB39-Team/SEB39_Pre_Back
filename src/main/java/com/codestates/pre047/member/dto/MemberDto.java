package com.codestates.pre047.member.dto;

import com.codestates.pre047.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotBlank(message = "Name can't be empty")
        @Size(message = "Name can't be more 15 characters", max = 15)
        private String username;

        @NotBlank(message = "Email can't be empty")
        @Email(message = "Invalid email")
        private String email;

        @NotBlank(message = "Password can't be empty")
        @Size(message = "Password can't be less 6 characters", min = 6)
        private String password;

        @NotBlank(message = "Password confirm can't be empty")
        private String passwordConfirm;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private long memberId;

        @NotBlank(message = "Name can't be empty")
        private String username;

        @NotBlank
        private String password;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        @NotBlank(message = "Name can't be empty")
        private String username;
    }


}