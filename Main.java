import java.util.Scanner;

import Controller.DatabaseController;
import Model.Database;

public class Main
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);


    }

}
