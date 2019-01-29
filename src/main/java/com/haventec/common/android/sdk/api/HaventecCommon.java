package com.haventec.common.android.sdk.api;

import com.haventec.common.android.sdk.api.exceptions.HaventecCommonException;
import com.haventec.common.android.sdk.helpers.HashingHelper;

public class HaventecCommon {

    public static byte[] generateSalt() throws HaventecCommonException {
        return HashingHelper.generateRandomSaltBytes();
    }

    public static String hashPin(String pin, byte[] salt) throws HaventecCommonException {
        return HashingHelper.createHash(pin, salt);
    }
}
