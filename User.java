import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {
    private String name,surname,username,password,salt,digest,mail;
    private byte[] encryptedSymmetric;

    public User(String name,String surname,String username,String password,String mail){
        this.name=name;
        this.surname=surname;
        this.username=username;
        this.password=password;
        this.mail=mail;
        this.salt= GenerateDigest.generateRandomSalt();
        this.digest= GenerateDigest.generateDigest(this.password,this.salt);
    }
    //=============================SETTERS================================================
    public void setEncryptedSymmetric(byte[] encryptedSymmetric) { this.encryptedSymmetric = encryptedSymmetric; }

    //============================GETTERS=================================================
    public String getSalt() {return salt;}

    public String getPassword() { return password; }

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
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", digest='" + digest + '\'' +
                ", mail='" + mail + '\'' +
                ", encryptedSymmetric=" + Arrays.toString(encryptedSymmetric) +
                '}';
    }
}
