package com.haventec.common.android.sdk.api.exceptions;

import com.haventec.common.android.sdk.models.HaventecError;

public enum CommonError implements HaventecError {
    /**
     * Secret Hashing Errors
     */
    HASHING_ERROR("CM-HASH-1001", "Hashing Error")
    ;

    private final String errorCode;
    private final String message;

    CommonError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * It prints the error in a specific format.
     *
     * @return human readable String with information about the Error
     */
    public String toString() {
        return " ErrorCode=" + this.getErrorCode() + ", Message=" +this.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
