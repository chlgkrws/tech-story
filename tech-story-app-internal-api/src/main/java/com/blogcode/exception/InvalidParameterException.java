package com.blogcode.exception;

import org.springframework.validation.Errors;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
public class InvalidParameterException extends CustomException{
    private static final long serialVersionUID = -2116671122895194101L;

    private final Errors errors;

    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

    public Errors getErrors() {
        return this.errors;
    }
}
