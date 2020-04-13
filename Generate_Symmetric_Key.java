import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Generate_Symmetric_Key {

    // Δημιουργια συμμετρικου κλειδιου
    public static SecretKey createSymmetricalKey() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] aeskey = secretKey.getEncoded();
            SecretKey secret_key = new SecretKeySpec(aeskey, "AES");
            return secret_key;
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] value,SecretKey secret_key) {
        try{
            Cipher aescipher = Cipher.getInstance("AES");;
            aescipher.init(Cipher.ENCRYPT_MODE,secret_key);
            return aescipher.doFinal(value);
        }catch (InvalidKeyException|BadPaddingException|IllegalBlockSizeException|NoSuchPaddingException|NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] encryption_value,SecretKey secret_key) {

        try{
            Cipher aescipher = Cipher.getInstance("AES");
            aescipher.init(Cipher.DECRYPT_MODE,secret_key);
            return aescipher.doFinal(encryption_value);
        }catch (InvalidKeyException|BadPaddingException|IllegalBlockSizeException|NoSuchPaddingException|NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return null;
    }

}
