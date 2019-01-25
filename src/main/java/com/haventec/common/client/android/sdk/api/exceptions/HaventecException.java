package com.haventec.common.client.android.sdk.api.exceptions;


import com.haventec.common.client.android.sdk.models.BaseResponse;
import com.haventec.common.client.android.sdk.models.HaventecError;

public class HaventecException extends Exception {

    private String errorCode;

    public HaventecException() {
    }

    public HaventecException(BaseResponse baseResponse) {

        super(baseResponse.getResponseStatus().getMessage());
        this.errorCode = baseResponse.getResponseStatus().getCode();
    }

    public HaventecException(HaventecError haventecError, String additionalInfo) {

        super(haventecError.getMessage() + ": " + additionalInfo);
        this.errorCode = haventecError.getErrorCode();
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
