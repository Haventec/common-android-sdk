package com.haventec.common.android.sdk.api;

import com.haventec.common.android.sdk.api.exceptions.HaventecCommonException;
import com.haventec.common.android.sdk.helpers.HashingHelper;

public class HaventecCommon {

    /**
     *  It generates a random salt
     *
     * @return byte[] random byte array
     */
    public static byte[] generateSalt() {
        return HashingHelper.generateRandomSaltBytes();
    }

    /**
     *  It creates a Hash of the pin, along with the salt
     *
     * @param pin
     * @param salt
     * @return String Base64-encoded representation of the SHA-512 hashed pin and salt
     * @throws HaventecCommonException
     */
    public static String hashPin(String pin, byte[] salt) throws HaventecCommonException {
        return HashingHelper.createHash(pin, salt);
    }
}
