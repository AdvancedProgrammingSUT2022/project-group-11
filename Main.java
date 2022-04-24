import Controller.DatabaseController;
import Model.Database;
import View.LoginMenu;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        LoginMenu loginMenu = new LoginMenu(databaseController);
        loginMenu.run(scanner);

    }

}
