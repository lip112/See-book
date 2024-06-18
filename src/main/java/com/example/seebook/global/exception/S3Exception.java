package com.example.seebook.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class S3Exception extends RuntimeException{

    private final ErrorCode errorCode;

    public S3Exception(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class EmptyFileException extends S3Exception{
        public EmptyFileException() {
            super(ErrorCode.EMPTY_FILE_EXCEPTION);
        }
    }

    public static class IoExceptionOnImageUpload extends S3Exception{
        public IoExceptionOnImageUpload() {
            super(ErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }
    }

    public static class NoFileExtention extends S3Exception{
        public NoFileExtention() {
            super(ErrorCode.NO_FILE_EXTENTION);
        }
    }

    public static class InvalidFileExtention extends S3Exception{
        public InvalidFileExtention() {
            super(ErrorCode.INVALID_FILE_EXTENTION);
        }
    }

    public static class PutObjectException extends S3Exception{
        public PutObjectException() {
            super(ErrorCode.PUT_OBJECT_EXCEPTION);
        }
    }
    public static class IoExceptionOnImageDelete extends S3Exception{
        public IoExceptionOnImageDelete() {
            super(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
        }
    }



}
