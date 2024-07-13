package com.example.seebook.global.exception;

import lombok.Getter;

@Getter
public class SupportException extends RuntimeException{
    private final ErrorCode errorCode;

    public SupportException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class NotFoundSupportException extends SupportException {
        public NotFoundSupportException() {
            super(ErrorCode.NOT_FOUND_SUPPORT);
        }
    }

}
