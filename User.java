
import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {
    private String name,surname,username,salt,digest,mail;
    private byte[] encryptedSymmetric;

    public User(String name,String surname,String username,String mail){
        this.name=name;
        this.surname=surname;
        this.username=username;
        this.mail=mail;
        this.salt= GenerateDigest.generateRandomSalt();
        this.digest=null;
    }
    //=============================SETTERS================================================
    public void setEncryptedSymmetric(byte[] encryptedSymmetric) { this.encryptedSymmetric = encryptedSymmetric; }

    public void setDigest(String digest) { this.digest = digest; }

    //============================GETTERS=================================================
    public String getSalt() {return salt;}

    public String getDigest() { return digest; }

    public String getUsername() { return username; }

    public byte[] getEncryptedSymmetric() { return encryptedSymmetric; }


    //============================to_STRING=================================================

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", digest='" + digest + '\'' +
                ", mail='" + mail + '\'' +
                ", encryptedSymmetric=" + Arrays.toString(encryptedSymmetric) +
                '}';
    }
}
