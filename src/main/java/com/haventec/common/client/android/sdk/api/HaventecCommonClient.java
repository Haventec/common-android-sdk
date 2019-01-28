package com.haventec.common.client.android.sdk.api;

import com.haventec.common.client.android.sdk.api.exceptions.HaventecException;
import com.haventec.common.client.android.sdk.helpers.HashingHelper;

public class Haventec {

    public static byte[] generateSalt() throws HaventecException {
        return HashingHelper.generateRandomSaltBytes();
    }

    public static String hashPin(String pin, byte[] salt) throws HaventecException {
        return HashingHelper.createHash(pin, salt);
    }
}
