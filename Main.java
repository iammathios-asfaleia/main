
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


public class Main {
    public static void main(String[] args) {

        //String value = "Hello world";
        //SecretKey key = Generate_Symmetric_Key.createSymmetricalKey();
//
        //System.out.println("The text value: " + value);
        //byte[] crypto_value = Generate_Symmetric_Key.encrypt(value.getBytes(),key);
        //System.out.println("The encrypted: " + crypto_value);
//
        //System.out.println("After the decryption: " + new String (Generate_Symmetric_Key.decrypt(crypto_value,key)));

//
        //String digest = GenerateDigest.generateDigest(user1.getPassword(), user1.getSalt());
        //System.out.println(digest);

        User user1 = new User("Dennis","Jo","Dionisis","123","d@gmail.com");
        System.out.println(user1.toString());
    }
}
