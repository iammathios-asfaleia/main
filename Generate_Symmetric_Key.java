import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Generate_Symmetric_Key {

    // Δημιουργια συμμετρικου κλειδιου
    public static SecretKey createSymmetricalKey() {
        KeyGenerator keyGenerator;
        try {
            // δημιουργια κλειδιου οπου θα εχει μεγεθος 256byte
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] aeskey = secretKey.getEncoded(); // περασμα κλειδιου σε πινακα απο byte[]
            SecretKey secret_key = new SecretKeySpec(aeskey, "AES"); // δημιουργια μυστικου κλειδιου 
            return secret_key;
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return null;
    }

    // Κρυπτογραφιση κλειδιου
    public static byte[] encrypt(byte[] value,SecretKey secret_key) {
        try{
            Cipher aescipher = Cipher.getInstance("AES"); // Δημιουργια αντικειμενου cipher οπου χρησιμοποιει τον αλγοριθμο AES 
            aescipher.init(Cipher.ENCRYPT_MODE,secret_key); // κρυπτογραφηση κλειδιου 
            return aescipher.doFinal(value); // επιστρεφομενη τιμη το κειμενο οπου κρυπτογραφειται 
        }catch (InvalidKeyException|BadPaddingException|IllegalBlockSizeException|NoSuchPaddingException|NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return null;
    }

    // Απο-κρυπτογραφιση κλειδιου
    public static byte[] decrypt(byte[] encryption_value,SecretKey secret_key) {

        try{
            Cipher aescipher = Cipher.getInstance("AES");
            aescipher.init(Cipher.DECRYPT_MODE,secret_key); // απο-κρυπτογραφηση κλειδιου 
            return aescipher.doFinal(encryption_value);// επιστρεφομενη τιμη η απο-κρυπτογραφηση του κρυπτογραφημενου κειμενου  
        }catch (InvalidKeyException|BadPaddingException|IllegalBlockSizeException|NoSuchPaddingException|NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return null;
    }

}
