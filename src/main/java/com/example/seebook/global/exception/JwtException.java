package com.example.seebook.global.exception;


import lombok.Getter;

@Getter
public class JwtException extends RuntimeException{
    private final ErrorCode errorCode;

    public JwtException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class SignatureException extends JwtException{
        public SignatureException() {
            super(ErrorCode.SIGNATURE_EXCEPTION);
        }
    }

    public static class MalformedJwtException extends JwtException{
        public MalformedJwtException() {
            super(ErrorCode.MALFORMED_JWT_EXCEPTION);
        }
    }

    public static class ExpiredJwtException extends JwtException{
        public ExpiredJwtException() {
            super(ErrorCode.EXPIRED_JWT_EXCEPTION);
        }
    }
}
