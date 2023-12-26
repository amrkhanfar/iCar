package icar;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users;  //temporary array list until a data base is created
    InstallationManager installationManager;

    public UserManager(InstallationManager installationManager){
        users = new ArrayList<User>(); //initializing the array list
        this.installationManager = installationManager;
        users.add( new User("Amr Khanfar", "ultraakch@gmail.com", "123456", Rank.ADMIN));
    }

    public User registerUser(String name, String email, String password, String role){
        if (getUserByEmail(email)!=null){
            return null;  //already exists
        }
        User newUser = new User(name, email, password, role);
        users.add(newUser);
        if (role.equals(Rank.INSTALLER)) {
            installationManager.registerInstaller(newUser);
        }
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
            if (user.getEmail().toLowerCase().equals(email.trim().toLowerCase())) {
                return user;  //Email exists in the database.
            }
        }
        return null;  //Email doesn't exist in the database.
    }

    public boolean deleteUser(User user) {

        if (users.contains(user)) {
            Boolean isUserObjectRemoved = users.remove(user);
            Boolean isInstallerRemoved = true;
            if (user.getRank().equals(Rank.INSTALLER)) {
                isInstallerRemoved = installationManager.removeInstaller(user);
            }

            return (isInstallerRemoved && isUserObjectRemoved);
        } else {
            return false;
        }

    }

    public User getUserByName(String name){
        for (User user : users){
            if (user.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                return user;  //Email exists in the database.
            }
        }
        return null;
    }

    public ArrayList<User> getUsersByRole(String role) {
        ArrayList<User> list = new ArrayList<User>();

        for (User user : users) {
            if (user.getRank().equals(role.trim().toLowerCase())) {
                list.add(user);
            }
        }

        return list;
    }

    public ArrayList<User> getUsers (){
        return users;
    }




}
