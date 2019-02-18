package com.haventec.common.android.sdk.helpers;

import com.haventec.common.android.sdk.api.exceptions.CommonError;
import com.haventec.common.android.sdk.api.exceptions.HaventecCommonException;

import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class HashingHelper {
    public static final String HASHING_ALGORITHM = "SHA-512";
    public static final int SALT_BYTE_SIZE = 128;

    /**
     * It generates a random salt
     *
     * @return byte[]
     */
    public static byte[] generateRandomSaltBytes() {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        return salt;
    }

    /**
     *  It creates a Hash of the text
     *
     * @param text String to hash with the salt
     * @param salt Salt to hash the text
     * @return String The hashed text
     * @throws HaventecCommonException If there is any issue generating the hash
     */
    public static String createHash(String text, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
            md.update(salt);
            byte[] bytes = md.digest(text.getBytes("UTF-8"));

            return toBase64(bytes);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new HaventecCommonException(CommonError.HASHING_ERROR, e);
        }
    }

    /**
     *  Encodes a byte array to a Base64-encoded String
     *
     * @param array byte array to parse to String
     *
     * @return String the parsed byte array
     *
     * @throws UnsupportedEncodingException
     *         If UTF-8 is not supported
     */
    public static String toBase64(byte[] array) throws UnsupportedEncodingException {

        if ( array == null || array.length < 1 ) {
            return null;
        }

        return new String(Base64.encodeBase64(array), "UTF-8");
    }

    /**
     * Decodes a Base64-encoded String to byte array
     *
     * @param hex String in Base64-encoded format
     * @return byte array with the content of the parameter <code>hex</code>
     * @throws UnsupportedEncodingException
     *         If UTF-8 is not supported
     */
    public static byte[] fromBase64(String hex) throws UnsupportedEncodingException {
        if ( hex == null ) {
            return null;
        }
        return Base64.decodeBase64(hex.getBytes("UTF-8"));
    }

}
