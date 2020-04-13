import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SomeClass {
    // credits https://www.geeksforgeeks.org/sha-256-hash-in-java/

    // hash the password & salt with SHA-256 into an array of bytes
    public static byte[] getSHA256(String psw, String salt) {

        MessageDigest md = null;
        String input = psw + salt; // string to hash
        try {
            md = MessageDigest.getInstance("SHA-256"); // Static getInstance method is called with hashing SHA (specify encryption)

            return md.digest(input.getBytes(StandardCharsets.UTF_8));// digest() method called to calculate message digest of an input and return array of bytes

        } catch (NoSuchAlgorithmException e) {
            System.out.println("! NoSuchAlgorithmException !\n");
            e.printStackTrace();

            return null;
        }
    }

    // generate random salt (10 letters)
    public static String generateRandomSalt() {

        byte[] array = new byte[10];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }

    // converts hexadecimal byte array into a String
    // returns the digest
    public static String toHexString(byte[] hash) {
        
        BigInteger number = new BigInteger(1, hash);    // Convert byte array into signum representation
        StringBuilder hexString = new StringBuilder(number.toString(16));   // Convert message digest into hex value

        while (hexString.length() < 32){    // Pad with leading zeros
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // checks if two digests are the same
    public static boolean digestEquality(String digest1, String digest2) {
        return (digest1.equals(digest2) ? true : false);
    }

    public static void main(String[] args){

        System.out.println("Hii \n");
        String salt = SomeClass.generateRandomSalt();

        String password1 = "safe123";
        String password2 = "ipourgos321";

        String digest1 = SomeClass.toHexString(SomeClass.getSHA256(password1, salt));
        String digest2 = SomeClass.toHexString(SomeClass.getSHA256(password2, salt));

        System.out.println("Hash password1 : " + digest1 );
        System.out.println("Hash password2 : " + digest2 + "\n" );
        System.out.println((SomeClass.digestEquality(digest1, digest2) ? "Equal\n" : "Not Equal\n"));
        System.out.println("Hash password2 : " + SomeClass.toHexString(SomeClass.getSHA256(password2, salt)) );

    }

}
