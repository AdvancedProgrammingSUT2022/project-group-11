package View;

import Controller.DatabaseController;
import Enums.MenuEnums;
import Model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu
{
    private DatabaseController databaseController;
    private User user;

    public ProfileMenu(DatabaseController databaseController, User user)
    {
        this.databaseController = databaseController;
        this.user = user;
    }

    public void run(Scanner scanner)
    {
        String input;
        Matcher matcher;
        while ( !(input = scanner.nextLine()).equals("menu exit"))
        {
            if ( (matcher = getCommandMatcher(input, MenuEnums.CHANGE_NICKNAME.getRegex())).matches())
            {
                this.databaseController.changeUserNickname(matcher, this.user);
            }
            else if ( (matcher = getCommandMatcher(input, MenuEnums.CHANGE_PASSWORD.getRegex())).matches())
            {
                this.databaseController.changePassword(matcher, this.user);
            }
            else
                System.out.println("invalid command");
        }
        return;
    }

    private Matcher getCommandMatcher(String input, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
