package com.example.seebook.global.exception;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException{
    private final ErrorCode errorCode;

    public BookException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class NotFoundBookException extends BookException {
        public NotFoundBookException() {
            super(ErrorCode.NOT_FOUND_BOOK);
        }
    }

}
