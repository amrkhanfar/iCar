package ICar;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;  //temporary array list until a data base is created

    public UserManager(){
        users = new ArrayList<User>(); //initializing the array list
    }

    public User registerUser(String name, String email, String password, String role){
        if (getUserByEmail(email)!=null){
            return null;
        }
        User newUser = new User(name, email, password, role);
        users.add(newUser);
        return newUser;
    }

    public User authenticateUser(String email, String password){
        for (User user : users){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;  //Authentication successful
            }
        }
        return null;  //Authentication fails. either password is not correct or account doesn't exist
    }

    public User getUserByEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email)) {
                return user;  //Email exists in the database.
            }
        }
        return null;  //Email doesn't exist in the database.
    }

    public User getUserByName(String name){
        for (User user : users){
            if (user.getName().equals(name)) {
                return user;  //Email exists in the database.
            }
        }
        return null;  //Email doesn't exist in the database.
    }

    public ArrayList<User> getUsers (){
        return users;
    }




}
