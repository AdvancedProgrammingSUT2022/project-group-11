package Controller;

import Model.Database;
import Model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class DatabaseController {
    private Database database;

    public DatabaseController (Database database)
    {
        this.database = database;
    }

    public void addUser (User user )
    {
        this.database.addUser(user);
    }

    public void createUser(Matcher matcher)
    {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");

        ArrayList<User> users = this.database.getUsers();

        for ( User user : users)
        {
            if ( user.getUsername().equals(username))
            {
                System.out.println("user with username " + username + " already exists");
                return ;
            }
            if ( user.getNickname().equals(nickname))
            {
                System.out.println("user with nickname " + nickname + " already exists");
                return ;
            }
        }

        User newUser = new User(username, password, nickname);
        this.database.addUser(newUser);
        System.out.println("user created successfully!");
        return ;
    }

    public User userLogin (Matcher matcher)
    {
        String username = matcher.group("username");
        String password = matcher.group("password");
        User user = this.database.getUserByUsernameAndPassword(username, password);
        if (user != null)
        {
            System.out.println("user logged in successfully!");
            return user;
        }
        System.out.println("Username and password didn't match!");
        return null;
    }

    public User getUserByUsername(String username)
    {
        return this.database.getUserByUsername(username);
    }

    public User getUserByNickname( String nickname)
    {
        return this.database.getUserByNickname(nickname);
    }

    public void changeUserNickname(Matcher matcher, User player)
    {
        String newNickname = matcher.group("newNickname");

        User user = getUserByNickname(newNickname);
        if ( user != null)
        {
            System.out.println("user with nickname " + newNickname + " already exists");
            return;
        }
        player.setNickname(newNickname);
        System.out.println("nickname changed successfully!");
    }

    public void changePassword(Matcher matcher, User user)
    {
        String currentPassword = matcher.group("currentPassword");
        String newPassword = matcher.group("newPassword");

        if ( !user.getPassword().equals(currentPassword))
        {
            System.out.println("current password is invalid");
            return;
        }
        if ( currentPassword.equals(newPassword))
        {
            System.out.println("please enter a new password");
            return;
        }
        user.setPassword(newPassword);
        System.out.println("password changed successfully!");
    }
}
