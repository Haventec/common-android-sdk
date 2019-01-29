package com.haventec.common.android.sdk.api;

import com.haventec.common.android.sdk.api.exceptions.HaventecCommonException;

import org.junit.Assert;
import org.junit.Test;

public class HaventecCommonTest {

    @Test
    public void generateRandomSalt() {

        byte[] salt = null;
        try {
            salt = HaventecCommon.generateSalt();
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }

        Assert.assertEquals(128, salt.length);
    }

    @Test
    public void hashPin() {

        byte[] salt = null;
        try {
            salt = HaventecCommon.generateSalt();
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));

        String hash2 = null;
        try {
            hash2 = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash2));
        Assert.assertTrue(hash.equals(hash2));

        String hash3 = null;
        try {
            hash3 = HaventecCommon.hashPin("1235", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash3));
        Assert.assertTrue(!hash.equals(hash3));
    }

    private static final Integer SHA512HASH_BASE64_LEN = 88;
    private static final String VALID_BASE64  = "^[A-Za-z0-9+\\/=]{1,}$";

    /**
     * Confirm that the pin is the correct length and only numbers
     *
     * @param pin The pin to validate
     * @return true if it meets the requirements
     */
    public static boolean isValidPin(String pin) {
        return (pin != null && isValidBase64(pin) && pin.length() == SHA512HASH_BASE64_LEN);
    }

    /**
     * Check the string conforms to base64 encoding standards
     *
     * @param strBase64 The incoming string
     * @return true is the string is the correct format
     */
    public static boolean isValidBase64(String strBase64) {
        return (strBase64 != null && strBase64.matches(VALID_BASE64) && (strBase64.length() % 4 == 0));
    }
}
