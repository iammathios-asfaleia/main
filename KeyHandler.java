import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.*;

public class KeyHandler {

    //================WRITE_PAIR_KEYS_TO_FILE==============================
    public static void writeAppsPairKeysToFile(){
        KeyPair keyPair = AssymetricKeyGenerator.getKeyPair();

        ObjectOutputStream objectOut=null;

        try{
            // Write public app key to file
            objectOut = new ObjectOutputStream(new FileOutputStream("publicKey"));
            objectOut.writeObject(keyPair.getPublic()); System.out.println("publicKey written");


            // Write private app key to file
            objectOut = new ObjectOutputStream(new FileOutputStream("privateKey"));
            objectOut.writeObject(keyPair.getPrivate()); System.out.println("privateKey written");

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                objectOut.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //================READ_PUBLIC_AES_KEY==============================
    public static PublicKey getAppPublicKey(){

        ObjectInputStream objectInput=null;

        try {
            objectInput = new ObjectInputStream(new FileInputStream("publicKey"));
            PublicKey publicKey = (PublicKey) objectInput.readObject();

            return publicKey;

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                objectInput.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    //================READ_PRIVATE_AES_KEY==============================
    public static PrivateKey getAppPrivateKey(){

        ObjectInputStream objectInput=null;

        try {
            objectInput = new ObjectInputStream(new FileInputStream("privateKey"));
            PrivateKey privateKey = (PrivateKey) objectInput.readObject();

            return privateKey;

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                objectInput.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    //======================CREATE_USER_READY_FOR_REGISTER + SYMMETRIC_KEY======================//
    public static User readyForRegister(User user){

        // Get secret key
        SecretKey userSecretKey = Generate_Symmetric_Key.createSymmetricalKey();
        // Get App public key
        PublicKey publicKey = KeyHandler.getAppPublicKey();

        try{
            Cipher encryptedSymmetric = Cipher.getInstance("RSA");
            encryptedSymmetric.init(Cipher.WRAP_MODE,publicKey);
            byte[] wrapped = encryptedSymmetric.wrap(userSecretKey);

            user.setEncryptedSymmetric(wrapped);

            return user;

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
