/**
 * This class gives the public method to be able to hash the String.
 * @author William Nguyen
 * @version 1.0
 */
public class Hashing extends SHA512 {
    /**
	 * Public method that is able to hash a string by calling it within any class.
	 * @param s the string that is being hashed
	 * @return encrypted string
	 */
    public static String cryptHash(String s) {
        String digest = hashSHA512(s);
        return digest.substring(0,128);
    }
}
