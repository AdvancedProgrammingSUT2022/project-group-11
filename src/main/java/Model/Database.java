package Model;

import java.util.ArrayList;

public class Database {
    private ArrayList<User> Users;

    

    public User getCivilizationUser(Terrain Terrain){
       for (User users : this.Users) {
           if(users.getCivilization().getTerrains().indexOf(Terrain) != -1){
               return users;
           }
       }
       return null;
    }

    public Database ()
    {
        this.Users = new ArrayList<>();
    }

    public void setUsers( ArrayList<User> Users){
        this.Users = Users;
    }
    public void addUser ( User user)
    {
        this.Users.add(user);
    }

    public ArrayList<User> getUsers()
    {
        return Users;
    }

    public User getUserByUsernameAndPassword (String username, String password)
    {
        for ( User user : this.Users)
        {
            if ( user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username)
    {
        for ( User user : this.Users)
        {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }
}
