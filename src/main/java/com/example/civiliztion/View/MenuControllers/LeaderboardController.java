package com.example.civiliztion.View.MenuControllers;


import com.example.civiliztion.Main;
import com.example.civiliztion.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.util.Objects;

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
    protected void onCancelButtonClick(ActionEvent event) throws IOException {
        switchToProfileMenu(event);
    }

        @FXML
        public void initialize() throws FileNotFoundException {


            ArrayList<User> users = Main.database.getUsers();
            users.sort(Comparator.comparing(User::getScore).reversed().thenComparing(User::getLastWin).thenComparing(User::getUsername));
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).equals(Main.database.getActiveUser())){
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

    public void switchToProfileMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("profileMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
