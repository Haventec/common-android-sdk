package com.haventec.common.client.android.sdk.helpers;

import com.haventec.common.client.android.sdk.api.exceptions.CommonClientSdkError;
import com.haventec.common.client.android.sdk.api.exceptions.HaventecException;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class HashingHelper {
    public static final String HASHING_ALGORITHM = "SHA-512";
    public static final int SALT_BYTE_SIZE = 128;

    /**
     * It generates a random salt
     *
     * @return
     */
    public static byte[] generateRandomSaltBytes() throws HaventecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        return salt;
    }
    public static String generateRandomSalt() throws HaventecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        try {
            return toBase64(salt);
        } catch (UnsupportedEncodingException e) {
            throw new HaventecException(CommonClientSdkError.SALT_GEN_ERROR, e);
        }
    }

    /**
     *  It create a Hash of the text
     *
     * @param text
     * @param salt
     * @return
     * @throws HaventecException
     */
    public static String createHash(String text, byte[] salt) throws HaventecException {
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
            md.update(salt);
            byte[] bytes = md.digest(text.getBytes("UTF-8"));

            return toBase64(bytes);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new HaventecException(CommonClientSdkError.HASHING_ERROR, e);
        }
    }


    private static byte[] fromBase64(String hex)
            throws IllegalArgumentException, UnsupportedEncodingException {
        return Base64.decodeBase64(hex.getBytes("UTF-8"));
    }

    private static String toBase64(byte[] array) throws UnsupportedEncodingException {
        return new String(Base64.encodeBase64(array), "UTF-8");
    }
}