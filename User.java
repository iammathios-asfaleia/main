import java.io.Serializable;

public class User implements Serializable {
    private String name,surname,username,password,salt;
    private static long serialVersionUID=1L;

    public User(String name,String surname,String username,String password,String salt){
        this.name=name;
        this.surname=surname;
        this.username=username;
        this.password=password;
        this.salt=SomeClass.generateRandomSalt();
    }

    public User(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

}
