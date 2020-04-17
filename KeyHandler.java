
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

    //======================FINALIZE_USER_TO_WRITE_TO_FILE======================//
    public static User finalizeUserAttributes(User user){

        SecretKey userSecretKey = Generate_Symmetric_Key.createSymmetricalKey(); // Get secret key
        PublicKey publicKey = KeyHandler.getAppPublicKey();// Get App public key

        byte[] encryptedSymmetric = AssymetricKeyGenerator.encryptWithPublic(userSecretKey,publicKey);
        user.setEncryptedSymmetric(encryptedSymmetric);

        return user;
    }

    //======================DECRYPT_SYMMETRIC_USER_KEY======================//
    public static SecretKey userDecryptSecretKey(User user){

        PrivateKey privateKey = KeyHandler.getAppPrivateKey(); // Get app private key
        byte[] encryptSymmetric = user.getEncryptedSymmetric(); // Get encrypt symmetric user key

        SecretKey decryptSymmetric = (SecretKey) AssymetricKeyGenerator.decryptWithPrivate(encryptSymmetric,privateKey);

        return decryptSymmetric;
    }

}
