import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException,
            BadPaddingException, InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException {

        String value = "Hello world";
        Generate_Symmetric_Key crypto_text = new Generate_Symmetric_Key();
        crypto_text.createSymmetricalKey();

        System.out.println("The text value: " + value);
        byte[] crypto_value = crypto_text.encrypt(value.getBytes());
        System.out.println("The encrypted: " + crypto_value);

        System.out.println("After the decryption: " + new String (crypto_text.decrypt(crypto_value)));

    }
}
