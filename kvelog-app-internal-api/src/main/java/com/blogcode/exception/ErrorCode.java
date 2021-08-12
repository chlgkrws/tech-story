package com.blogcode.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Description : api error code
 *
 * @author leejinho
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(400, null, "Invalid Request Data"),
    COUPON_EXPIRATION(410, "C001", "Coupon Was Expired"),
    COUPON_NOT_FOUND(404, "C002", "Coupon Not Found");

    private final int status;
    private final String code;
    private final String message;
}
