package com.haventec.common.android.sdk.api;

import com.haventec.common.android.sdk.api.exceptions.HaventecCommonException;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class HaventecCommonHashingTests {

    @Test
    public void generateRandomSalt() {

        HashSet<String> saltGenerated = new HashSet<>();

        for(int i=0; i< 1000; i++) {
            byte[] salt = HaventecCommon.generateSalt();
            Assert.assertEquals(128, salt.length);
            Assert.assertFalse(saltGenerated.contains(salt.toString()));
            saltGenerated.add(salt.toString());
        }
    }

    /**
     * Main success path for hashPin
     */
    @Test
    public void hashPin_Success() {

        byte[] salt = HaventecCommon.generateSalt();

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));
    }


    /**
     * Ensure that the same salt and same PIN always results in the same hash
     */
    @Test
    public void hashPin_SamePinSameSalt() {

        byte[] salt = HaventecCommon.generateSalt();

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));

        for(int i=0; i<100; i++) {
            String hash2 = null;
            try {
                hash2 = HaventecCommon.hashPin("1234", salt);
            } catch (HaventecCommonException e) {
                e.printStackTrace();
                Assert.fail();
            }
            Assert.assertTrue(isValidPin(hash2));
            Assert.assertTrue(hash.equals(hash2));
        }
    }

    /**
     * Same salt with different PIN should give us completely different hashedPin
     */
    @Test
    public void hashPin_SameSaltDifferentPin() {

        byte[] salt = HaventecCommon.generateSalt();

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));

        for(int i=0; i<100; i++) {
            String hash2 = null;
            try {
                hash2 = HaventecCommon.hashPin("1235", salt);
            } catch (HaventecCommonException e) {
                e.printStackTrace();
                Assert.fail();
            }
            Assert.assertTrue(isValidPin(hash2));
            Assert.assertTrue(!hash.equals(hash2));
        }
    }

    /**
     * Different salt with same PIN should give us completely different hashedPin
     */
    @Test
    public void hashPin_DifferentSaltSamePin() {
        String commonPin = "2233";

        String hash = null;
        try {
            hash = HaventecCommon.hashPin(commonPin, HaventecCommon.generateSalt());
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));

        for(int i=0; i<100; i++) {
            String hash2 = null;
            try {
                hash2 = HaventecCommon.hashPin(commonPin, HaventecCommon.generateSalt());
            } catch (HaventecCommonException e) {
                e.printStackTrace();
                Assert.fail();
            }
            Assert.assertTrue(isValidPin(hash2));
            Assert.assertTrue(!hash.equals(hash2));
        }
    }


    /**
     * Edge-case tests
     */

    @Test
    public void hashPin_Fail_Null_Salt() {

        byte[] salt = null;

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertNull(hash);
    }

    @Test
    public void hashPin_Fail_Empty_Salt() {

        byte[] salt = new byte[64];

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));
    }

    @Test
    public void hashPin_Fail_Bad_Salt() {

        byte[] salt = new byte[64];
        salt[0] = 0;
        salt[1] = 1;
        salt[2] = 2;
        salt[3] = 3;
        salt[4] = 4;
        salt[5] = 5;
        salt[6] = 6;

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("1234", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));
    }

    @Test
    public void hashPin_Fail_Null_Pin() {

        byte[] salt = null;
        salt = HaventecCommon.generateSalt();

        String hash = null;
        try {
            hash = HaventecCommon.hashPin(null, salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertNull(hash);
    }

    @Test
    public void hashPin_Fail_Blank_Pin() {

        byte[] salt = null;
        salt = HaventecCommon.generateSalt();

        String hash = null;
        try {
            hash = HaventecCommon.hashPin("", salt);
        } catch (HaventecCommonException e) {
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertTrue(isValidPin(hash));
    }


    private static final Integer SHA512HASH_BASE64_LEN = 88;
    private static final String VALID_BASE64  = "^[A-Za-z0-9+\\/=]{1,}$";

    /**
     * Confirm that the PIN is the correct length and only numbers
     *
     * @param pin The PIN to validate
     * @return true if it meets the requirements
     */
    private static boolean isValidPin(String pin) {
        return (pin != null && isValidBase64(pin) && pin.length() == SHA512HASH_BASE64_LEN);
    }

    /**
     * Check the string conforms to base64 encoding standards
     *
     * @param strBase64 The incoming string
     * @return true is the string is the correct format
     */
    private static boolean isValidBase64(String strBase64) {
        return (strBase64 != null && strBase64.matches(VALID_BASE64) && (strBase64.length() % 4 == 0));
    }
}
