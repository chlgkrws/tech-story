package com.blogcode.exception;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
