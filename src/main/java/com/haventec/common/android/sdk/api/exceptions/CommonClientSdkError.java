package com.haventec.common.android.sdk.api.exceptions;

import com.haventec.common.android.sdk.models.HaventecError;

public enum CommonClientSdkError implements HaventecError {
    /**
     * Encryption Errors
     */
    ENCRYPTION_ERROR("AN-CRYP-1001", "Encryption Error"),
    DECRYPTION_ERROR("AN-CRYP-1002", "Decryption Error"),

    /**
     * Secret Hashing Errors
     */
    HASHING_ERROR("AN-HASH-1001", "Hashing Error"),
    INVALID_BASE64_ENCODED("AN-HASH-1002", "Invalid Base64 encoded"),
    INVALID_HASH_SECTIONS("AN-HASH-1003", "The number of section of this hash is not correct"),
    HASH_FORMAT_NOT_SUPPORTED("AN-HASH-1004", "The hash format is not supported"),
    INVALID_SECRET_FORMAT("AN-HASH-1005", "Invalid secret format"),
    INVALID_SALT_FORMAT("AN-HASH-1006", "Invalid salt format"),
    SALT_GEN_ERROR("AN-HASH-1007", "Salting error"),
    ;



    private final String errorCode;
    private final String message;
    CommonClientSdkError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * It prints the AutenticateError in a specific format.
     *
     * @return
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
