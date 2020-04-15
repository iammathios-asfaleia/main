
public class Main {
    public static void main(String[] args) {

        User user1 = new User("dennis","Jo","dionisis","email@gmail.com");
        User user2 = new User("John","Ske","giannis","email1@gmail.com");

        Login_Register.register(user1,"123");
        Login_Register.register(user2,"321");

        Login_Register.file();

        boolean login1 = Login_Register.login("456");
        boolean login2 = Login_Register.login("321");

    }
}
