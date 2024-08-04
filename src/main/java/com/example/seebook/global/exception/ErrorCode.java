package com.example.seebook.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //User
    PHONE_NUMBER_DUPLICATION(HttpStatus.BAD_REQUEST, "이미 가입된 핸드폰 번호 입니다."),
    NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "찾을 수 없는 계정 입니다"),
    NOT_FOUND_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "찾을 수 없는 전화번호 입니다"),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "아이디 및 비밀번호가 일치하지 않습니다."),
    INVALID_VERIFICATION_CODE_EXCEPTION(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않습니다."),
    NOT_KAKAO_ACCOUNT_EXCEPTION(HttpStatus.BAD_REQUEST, "카카오로 가입된 회원이 아닙니다."),

   //review
    NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "찾을 수 없는 리뷰입니다"),
    NOT_MATCH_USER_EXCEPTION(HttpStatus.BAD_REQUEST, "본인이 작성한 리뷰가 아닙니다."),

    //Book
    NOT_FOUND_BOOK(HttpStatus.BAD_REQUEST, "찾을 수 없는 책입니다"),

    //S3
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    NO_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    INVALID_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    PUT_OBJECT_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지를 업로드 할 수 없습니다"),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),


    //jwt
    SIGNATURE_EXCEPTION(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),
    MALFORMED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다."),
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다. 다시 로그인해주세요."),

    //Report
    NOT_FOUND_REPORT(HttpStatus.BAD_REQUEST, "존재 하지 않는 reportId입니다."),

    //support
    NOT_FOUND_SUPPORT(HttpStatus.BAD_REQUEST, "존재 하지 않는 supportId입니다.");


    private final HttpStatus ErrorCode;
    private final String message;

}
