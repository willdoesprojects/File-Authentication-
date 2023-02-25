/**
 * This class is the hash method/encryption method for the string.
 * @author William Nguyen
 * @version 1.0
 */

import java.security.*;
import java.nio.charset.*;

public class SHA512 {
    /**
	 * This method hashes the actual the string from the input.
	 * @param message the string/file that is being hashed
	 * @return the encrypted key
	 */
    protected static String hashSHA512(String message) {
        String sha512ValueHexa = "";
        try {
            MessageDigest digest512 = MessageDigest.getInstance("SHA-512");
            sha512ValueHexa = byteToHex(digest512.digest(message.getBytes(StandardCharsets.UTF_8)));
        }
        catch(NoSuchAlgorithmException exp) {
            exp.getMessage();
        }
        return sha512ValueHexa;
    }
    /**
     * Converts the byte values to hex/character values.
     * @param digest the bytes
     * @return output values
     */
    public static String byteToHex(byte[] digest) {
        StringBuilder vector = new StringBuilder();
        for (byte c : digest) {
            vector.append(String.format("%02X", c));
        }
        String output = vector.toString();
        return output;
    }
}
