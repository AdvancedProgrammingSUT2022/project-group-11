import java.util.Scanner;

import Controllers.DatabaseController;
import Model.Database;
import View.LoginMenu;
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        LoginMenu loginMenu = new LoginMenu(databaseController);
        loginMenu.run(scanner);        
    }
}