package com.example.seebook.global.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class DuplicatedPhoneNumberException extends UserException {
        public DuplicatedPhoneNumberException() {
            super(ErrorCode.PHONE_NUMBER_DUPLICATION);
        }
    }

    public static class NotFoundEmailException extends UserException {
        public NotFoundEmailException() {
            super(ErrorCode.NOT_FOUND_EMAIL);
        }
    }

    public static class LoginFailedException extends UserException {
        public LoginFailedException() {
            super(ErrorCode.LOGIN_FAILED);
        }
    }

    public static class NotFoundPhoneNumberException extends UserException {
        public NotFoundPhoneNumberException() {
            super(ErrorCode.NOT_FOUND_PHONE_NUMBER);
        }
    }

    public static class NotFoundUserException extends UserException {
        public NotFoundUserException() {
            super(ErrorCode.NOT_FOUND_USER);
        }
    }
    public static class InvalidVerificationCodeException extends UserException {
        public InvalidVerificationCodeException() {
            super(ErrorCode.INVALID_VERIFICATION_CODE_EXCEPTION);
        }
    }

    public static class NotKakaoAccountException extends UserException {
        public NotKakaoAccountException() {
            super(ErrorCode.NOT_KAKAO_ACCOUNT_EXCEPTION);
        }
    }


}
