package com.example.civilization.FXMLcontrollers;


import com.example.civilization.Main;
import com.example.civilization.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardController {
    @FXML
    ArrayList<TextField> playersTime = new ArrayList<>();
    @FXML
    ArrayList<TextField> playersHighScore = new ArrayList<>();
    @FXML
    ArrayList<TextField> playersUsername = new ArrayList<>();
    @FXML
    ArrayList<ImageView> playersAvatar = new ArrayList<>();
    @FXML
    ArrayList<TextField> playersLastLogin = new ArrayList<>();


    @FXML
    public void initialize() throws FileNotFoundException {


        ArrayList<User> users = Main.database.getUsers();
        users.sort(Comparator.comparing(User::getScore).reversed().thenComparing(User::getLastWin).thenComparing(User::getUsername));
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(Main.database.getActiveUser())) {
                playersUsername.get(i).setStyle("-fx-background-color: GREEN;");
                playersTime.get(i).setStyle("-fx-background-color: GREEN;");
                playersHighScore.get(i).setStyle("-fx-background-color: GREEN;");
                playersLastLogin.get(i).setStyle("-fx-background-color: GREEN;");
            }
            playersUsername.get(i).setText(users.get(i).getUsername());
            playersTime.get(i).setText(users.get(i).getLastWin());
            playersHighScore.get(i).setText(Integer.toString(users.get(i).getScore()));
            //   playersAvatar.get(i).setImage(new Image(new FileInputStream(users.get(i).getProfilePicture())));
            playersLastLogin.get(i).setText(users.get(i).getLastLogin());
        }


    }

    public void backToMainMenu(ActionEvent event) throws IOException {
        Main.changeMenu("ProfileMenu");
    }


}
