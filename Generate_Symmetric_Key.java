import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Generate_Symmetric_Key {

    private Cipher aescipher;
    private byte[] aeskey;
    private SecretKeySpec aeskeySpec;

    public void createSymmetricalKey() throws NoSuchAlgorithmException
    {
        KeyGenerator keyGenerator;
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        aeskey = secretKey.getEncoded();
        aeskeySpec = new SecretKeySpec(aeskey,"AES");
    }

    public byte[] encrypt(byte[] value) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            NoSuchPaddingException, NoSuchAlgorithmException {

        aescipher = Cipher.getInstance("AES");
        aescipher.init(Cipher.ENCRYPT_MODE,aeskeySpec);
        return aescipher.doFinal(value);
    }

    public byte[] decrypt(byte[] encryption_value) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            NoSuchPaddingException, NoSuchAlgorithmException {

        aescipher = Cipher.getInstance("AES");
        aescipher.init(Cipher.DECRYPT_MODE,aeskeySpec);
        return aescipher.doFinal(encryption_value);
    }

}
