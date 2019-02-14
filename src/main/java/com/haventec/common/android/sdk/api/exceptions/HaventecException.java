package com.haventec.common.android.sdk.api.exceptions;

import com.haventec.common.android.sdk.models.HaventecError;

/**
 * All Haventec exceptions at Android SDKs extend this runtime exception.
 *
 * It's an uncheck exception in order to give flexibility to developers
 * but we do recommend to always catch it, analyse the error and act accordingly.
 *
 */
public abstract class HaventecException extends RuntimeException {
    protected String errorCode;

    public HaventecException() {
        super();
    }

    public HaventecException(HaventecError haventecError, Throwable throwable) {
        super(haventecError.getMessage() + ": " + throwable.getMessage(), throwable);
        this.errorCode = haventecError.getErrorCode();
    }

    public HaventecException(HaventecError haventecError) {
        super(haventecError.getMessage());
        this.errorCode = haventecError.getErrorCode();
    }
}
