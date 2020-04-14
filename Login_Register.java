
import java.io.*;

public class Login_Register {

    //=====================================REGISTER=============================================================//
    public static void register(User user){
        File register_file = new File("Register_Users");
        ObjectOutputStream Object_out = null;
        FileOutputStream File_out = null;

        try{
            // Check if the file already exists
            if(register_file.exists()){
                // Check if the username exists into the file (if no->append->write) (if yes->system.out.println)
                if(checkUsernameExists(user.getUsername()) == false)
                {
                    // Append file because header already written
                    File_out = new FileOutputStream(register_file,true);
                    Object_out = new ObjectOutputStream(File_out){
                        @Override
                        public void writeStreamHeader() {
                        }
                    };

                    Object_out.writeObject(KeyHandler.readyForRegister(user));
                }else{
                    System.out.println(user.getUsername() + " Can't register!");
                }
                // New file with the new user register
            }else{
                File_out = new FileOutputStream(register_file);
                Object_out = new ObjectOutputStream(File_out);
                Object_out.writeObject(KeyHandler.readyForRegister(user));
                System.out.println("1st time in!");
            }

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(Object_out!=null){
                    Object_out.flush();
                    Object_out.close();
                    File_out.flush();
                    File_out.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    //=====================================LOGIN=============================================================//
    public static void login(String username, String password){

    }

    //=====================================CHECK_USERNAME_IF_EXISTS=============================================================//
    public static boolean checkUsernameExists(String username){

        boolean username_found = false;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Register_Users"));
            while (true){
                try{
                    // Read object from file
                    User user_temp = (User) ois.readObject();
                    if(user_temp.getUsername().equals(username)) {
                        username_found = true;
                        break;
                    }
                }catch (ClassNotFoundException ex){
                    ex.printStackTrace();
                }catch (EOFException ex){
                    if(username_found==false)
                        System.out.println("Scanning-> Username not found");
                    break;
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return username_found;
    }

    //=====================================SHOW_FILE_VALUES=============================================================//
    public static void file(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Register_Users"));

            while (true){
                try {
                    User tempUser = (User) ois.readObject();
                    System.out.println("Username is : " + tempUser.getUsername());

                }catch (EOFException ex){ System.out.println("eof"); break;
                }
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
