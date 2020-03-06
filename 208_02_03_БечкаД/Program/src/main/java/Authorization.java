import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Authorization {
    private ArrayList<User> users_in_system;
    private Scanner input;
    private int login_try = 0;
    private User currentUser;

    public Authorization(String address) throws FileNotFoundException, EOFException{
        if(!readDataBase(address)){
            throw new EOFException("The file is Empty!");
        }
        input = new Scanner(System.in);
    }


    private boolean readDataBase(String address) throws FileNotFoundException {
        input = new Scanner(new File(address));
        if(!input.hasNextLine()){
            return false;
        }
        users_in_system = new ArrayList<>(0);
        while(input.hasNextLine()){
            users_in_system.add(new User(input.next(), input.next(), Status.UNAUTHORIZED));
        }
        input.close();
        return true;
    }

    private void enterLogin() {
        while (true) {
            System.out.println("Enter login: ");
            String login = input.nextLine();
            currentUser = this.getCurrentUser(login);
            if(!correctLogin(login) || currentUser == null) {
                System.out.println("Incorrect login!");
                continue;
            }
            else if(currentUser.getStatus() == Status.BLOCKED || currentUser.getLoginTry() >= 4){
                System.out.println("Login: " + login + " is blocked!");
                continue;
            }
            else if(currentUser.getStatus() == Status.AUTHORIZED){
                System.out.println("This user is already logged in!");
            }
            else{
                break;
            }
        }
    }

    private boolean enterPassword(User user){
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter password: ");
            String password = input.nextLine();
            if (user.checkPassword(password)) {
                return true;
            }
            currentUser.increaseLoginTry();
            System.out.println("Incorrect password!");
        }
        user.setStatus(Status.BLOCKED);
        System.out.println("Login: " + user.getLogin() + " is blocked!");
        return false;
    }

    private boolean correctLogin(String login){
        return Pattern.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}",login);
    }

    public void singIn() throws AuthorizationException{
        while (login_try <= 7){
             this.enterLogin();
            if(this.enterPassword(currentUser)){
                currentUser.setStatus(Status.AUTHORIZED);
                System.out.println("Success!");
                login_try = 0;
                return;
            }
            login_try++;
        }
        throw new AuthorizationException("You have exhausted all attempts to log in!");
    }

    private User getCurrentUser(String login){
        for (User user : users_in_system) {
            if(login.equals(user.getLogin())){
                return user;
            }
        }
        return null;
    }

}

class User{
    private String login;
    private String password;
    private Status user_status;
    private int login_try;

    public User(String login, String password, Status user_status){
        this.login = login;
        this.password = password;
        this.user_status = user_status;
        this.login_try = 0;
    }

    //get
    public String getLogin(){
        return login;
    }
    public Status getStatus(){
        return user_status;
    }
    public int getLoginTry(){
        return login_try;
    }
    //set
    public void setStatus(Status new_status){
        this.user_status = new_status;
    }

    //method
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public void increaseLoginTry(){
        login_try++;
    }

}

enum Status{
    UNAUTHORIZED, AUTHORIZED, BLOCKED
}
