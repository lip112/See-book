package com.example.seebook.global.exception;

import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException{
    private final ErrorCode errorCode;

    public ReviewException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class NotFoundReviewIdException extends ReviewException{
        public NotFoundReviewIdException() {
            super(ErrorCode.NOT_FOUND_REVIEW);
        }
    }

}
