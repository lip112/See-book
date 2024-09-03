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
    public static class NotMatchUserException extends ReviewException{
        public NotMatchUserException() {
            super(ErrorCode.NOT_MATCH_USER_EXCEPTION);
        }
    }
    public static class NotFoundReviewException extends ReviewException{
        public NotFoundReviewException() {
            super(ErrorCode.NOT_FOUND_REVIEW);
        }
    }
}
