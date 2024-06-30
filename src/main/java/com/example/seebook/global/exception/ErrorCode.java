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

   //review
   NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "찾을 수 없는 리뷰입니다"),

    //Book
    NOT_FOUND_BOOK(HttpStatus.BAD_REQUEST, "찾을 수 없는 책입니다"),

    //S3
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    NO_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    INVALID_FILE_EXTENTION(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다"),
    PUT_OBJECT_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지를 업로드 할 수 없습니다"),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일 입니다");


    private final HttpStatus ErrorCode;
    private final String message;

}
