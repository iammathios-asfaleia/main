
import javax.crypto.SecretKey;


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

        User user1 = new User("Babis");
        User user2 = new User("Babis");
        User user3 = new User("Kostas");
        User user4 = new User("Anna");

        Login_Register.register(user1);
        Login_Register.register(user3);
        Login_Register.register(user2);
        Login_Register.register(user4);
        Login_Register.file();
    }
}
