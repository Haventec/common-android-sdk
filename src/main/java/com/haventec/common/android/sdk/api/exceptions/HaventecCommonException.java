package com.haventec.common.android.sdk.api.exceptions;

import com.haventec.common.android.sdk.models.HaventecError;

public class HaventecCommonException extends HaventecException {
    public HaventecCommonException(HaventecError haventecError, Throwable throwable) {
        super(haventecError, throwable);
    }

    public HaventecCommonException(HaventecError haventecError) {
        super(haventecError);
    }
}
