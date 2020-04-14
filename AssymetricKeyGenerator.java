
import java.io.*;
import java.security.*;
import java.util.logging.*;
import javax.crypto.*;

public final class AssymetricKeyGenerator {

    //-----------------------------------------------------PAIR_KEY-----------------------------------------------------
    public static KeyPair getKeyPair(){
        
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");//Specify encrypting algorithm (RSA)
            generator.initialize(2048, new SecureRandom());//2048=bit size
            //SecureRandom is chosen to comply with FIPS 140-2, Security Requirements for Cryptographic Modules
            //https://csrc.nist.gov/publications/detail/fips/140/2/final
            
            KeyPair keyPair = generator.generateKeyPair();https://docs.oracle.com/javase/8/docs/api/java/security/KeyPairGenerator.html
            
            return keyPair;
            
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    
    //-----------------------------------------------------ENCRYPTION-----------------------------------------------------
    
    public static byte[] encryptWithPublic(Object plainText, PublicKey publicKey){
        
        try {
            //encryption set up
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            byte[] serializedPlain=serialize(plainText);//object->serialization->byte[]
            
            byte[] cipherText = encryptCipher.doFinal(serializedPlain);//byte[]->encryption->encrypted byte[]
            
            return cipherText;
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public static byte[] encryptWithPrivate(Object plainText, PrivateKey privateKey){
        
        try {
            
            //encryption set up
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
            
            byte[] serializedPlain=serialize(plainText);//object->byte[]
            
            byte[] cipherText = encryptCipher.doFinal(serializedPlain);//byte[]->encryption->encrypted byte[]
            
            return cipherText;
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    //-----------------------------------------------------ENCRYPTION-----------------------------------------------------
    
    
    
    
    //-----------------------------------------------------DECRYPTION-----------------------------------------------------
    
    public static Object decryptWithPublic(byte[] cipherText, PublicKey publicKey){
        try {
            
            //decryption set up
            Cipher decryptionCipher = Cipher.getInstance("RSA");
            decryptionCipher.init(Cipher.DECRYPT_MODE, publicKey);
            
            byte[] serializedObject=decryptionCipher.doFinal(cipherText);//encrypted byte[]->decryption->byte[]
            Object plainText=deserialize(serializedObject);//byte[]->object
            return plainText;
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "failed public decryption";
    }
    
    public static Object decryptWithPrivate(byte[] cipherText, PrivateKey privateKey){
        try {
            
            //decryption set up
            Cipher decryptionCipher = Cipher.getInstance("RSA");
            decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            byte[] serializedObject=decryptionCipher.doFinal(cipherText);//encrypted byte[]->decryption->byte[]
            Object plainText=deserialize(serializedObject);//byte[]->object
            return plainText;
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "failed public decryption";
    }
    
    //-----------------------------------------------------DECRYPTION-----------------------------------------------------
    
    
    
    //-----------------------------------------------------SERIALIZATION-----------------------------------------------------
    
    //https://stackoverflow.com/questions/3736058/java-object-to-byte-and-byte-to-object-converter-for-tokyo-cabinet/3736091
    public static byte[] serialize(Object obj){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Object deserialize(byte[] data){
        ObjectInputStream is = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(AssymetricKeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    //-----------------------------------------------------SERIALIZATION-----------------------------------------------------
}
