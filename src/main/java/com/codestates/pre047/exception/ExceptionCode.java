package com.codestates.pre047.exception;

import lombok.Getter;

public enum ExceptionCode {
    POST_NOT_FOUND(404, "Post Not Found"),
    MEMBER_EXIST(404,"Member Already Exist"),
    INFO_NOT_FOUND(404,"Info Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
