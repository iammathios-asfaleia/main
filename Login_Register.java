/*
 * Icsd14130 Ματθαίος Μπεγκβάρφαϊ
 */
package testregister;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login_Register {

    public static void register(User user){
        File register_file = new File("Register_Users");
        //ObjectOutputStream Object_file_out = new ObjectOutputStream(new FileOutputStream("Register_Users"));
        ObjectOutputStream Object_out = null;
        FileOutputStream File_out = null;

        try{
            // Check if the file already exists
            if(register_file.exists()){
                // Check if the username exists into the file (if no->append->write) (if yes->system.out.println)
                if(checkUsernameExists(user) == false)
                {
                    // Append file because header already written
                    File_out = new FileOutputStream(register_file,true);
                    Object_out = new ObjectOutputStream(File_out){
                        @Override
                        public void writeStreamHeader() {
                        }
                    };
                    Object_out.writeObject(user);
                }else{
                    System.out.println(user.getUsername() + " Can't register!");
                }
                // New file with the new user register
            }else{
                File_out = new FileOutputStream(register_file);
                Object_out = new ObjectOutputStream(File_out);
                System.out.println("1st time in!");
                Object_out.writeObject(user);
            }
            

            
            Object_out.flush();
            Object_out.close();
            File_out.flush();
            File_out.close();
            
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> file(){
        
        ArrayList<User> storedUsers=new ArrayList<User>();
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Register_Users"));
            
            
            User tempUser = (User) ois.readObject();
            System.out.println("Username is : " + tempUser.getUsername());
            
            try{
            while(tempUser!=null){
                tempUser=(User)ois.readObject();
                System.out.println("Username is : " + tempUser.getUsername());
                storedUsers.add(tempUser);
            }
            
            }catch(EOFException eof){
                
            }

                

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return storedUsers;
    }

    // Check if username already exists
    public static boolean checkUsernameExists(User user){
        
        ArrayList<User> storedUsers=file();
        
        if(file()!=null){
            return storedUsers.contains(user);//exists or not
        }
        return false;//file empty
    }

    public static void login(){

    }
}