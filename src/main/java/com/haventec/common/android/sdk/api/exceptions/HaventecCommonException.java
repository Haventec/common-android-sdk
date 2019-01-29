package com.haventec.common.android.sdk.api.exceptions;


import com.haventec.common.android.sdk.models.BaseResponse;
import com.haventec.common.android.sdk.models.HaventecError;

public class HaventecCommonException extends Exception {

    private String errorCode;

    public HaventecCommonException() {
    }

    public HaventecCommonException(BaseResponse baseResponse) {

        super(baseResponse.getResponseStatus().getMessage());
        this.errorCode = baseResponse.getResponseStatus().getCode();
    }

    public HaventecCommonException(HaventecError haventecError, String additionalInfo) {

        super(haventecError.getMessage() + ": " + additionalInfo);
        this.errorCode = haventecError.getErrorCode();
    }

    public HaventecCommonException(HaventecError haventecError, Throwable throwable) {

        super(haventecError.getMessage() + ": " + throwable.getMessage(), throwable);
        this.errorCode = haventecError.getErrorCode();
    }

    public HaventecCommonException(HaventecError haventecError) {
        super(haventecError.getMessage());
        this.errorCode = haventecError.getErrorCode();
    }
}
