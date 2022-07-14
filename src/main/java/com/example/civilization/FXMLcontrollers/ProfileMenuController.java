package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.Database;
import com.example.civilization.Model.Map;
import com.example.civilization.Model.User;
import com.example.civilization.Requests.RequestUser;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileMenuController {
    public static DatabaseController databaseController = DatabaseController.getInstance();
    @FXML
    private Label username;
    @FXML
    private Label nickname;
    @FXML
    private ImageView photo;
    @FXML
    private TextField editNickname;
    @FXML
    private TextField editPassword;
    @FXML
    private ImageView profilePic;
    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView pic4;
    @FXML
    private ImageView pic5;
    @FXML
    private ImageView pic6;
    @FXML
    private ImageView pic7;
    @FXML
    private ImageView pic8;
    @FXML
    private Label nicknameWarning;
    @FXML
    private Label passwordWarning;




    public void setTexts() throws FileNotFoundException {
        User activeUser = databaseController.activeUser();
        username.setText(activeUser.getUsername());
        nickname.setText(activeUser.getNickname());
        Image image = new Image(new FileInputStream(activeUser.getProfilePicture()));
        //Image image = new Image( ProfileMenuController.class.getResource("/com/example/civilization/PNG/prof" + Database.getInstance().getActiveUser().photoNumber + ".png").toString());
        photo.setImage(image);


    }

    public void setUpEditPage() {
        User activeUser = databaseController.activeUser();
        editNickname.setPromptText(activeUser.getNickname());
        editPassword.setPromptText(activeUser.getPassword());
        Image image;
        try {
            image = new Image(new FileInputStream(activeUser.getProfilePicture()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Image image = new Image(ProfileMenuController.class.getResource("/com/example/civilization/PNG/prof" + Database.getInstance().getActiveUser().photoNumber + ".png").toString());
        profilePic.setImage(image);


    }

    public void changePicTo1() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic1.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof1.png");
    }

    public void changePicTo2() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic2.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof2.png");
    }

    public void changePicTo3() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic3.getImage());
       activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof3.png");

    }

    public void changePicTo4() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic4.getImage());
       activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof4.png");
    }

    public void changePicTo5() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic5.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof5.png");

    }

    public void changePicTo6() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic6.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof6.png");
    }

    public void changePicTo7() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic7.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof7.png");
    }

    public void changePicTo8() {
        User activeUser = databaseController.activeUser();
        profilePic.setImage(pic8.getImage());
        activeUser.setProfilePicture("src/main/resources/com/example/civilization/PNG/images/prof8.png");
    }

    public void saveChanges() {
        String newNickname = editNickname.getText();
        String newPassword = editPassword.getText();
        User activeUser = databaseController.activeUser();
        if (!newNickname.equals("")) {
            String temp = databaseController.changeUserNickname(newNickname, activeUser);
            if (temp.startsWith("user")) {
                nicknameWarning.setText(temp);
                nicknameWarning.setVisible(true);
            } else {
                nicknameWarning.setVisible(false);
            }
        }
        if (!newPassword.equals("")) {
            String passTemp = databaseController.changePassword(newPassword, activeUser);
            if (passTemp.startsWith("please")) {
                passwordWarning.setText(passTemp);
                passwordWarning.setVisible(true);

            } else {
                passwordWarning.setVisible(false);
            }

        }
        if (!passwordWarning.isVisible() && !nicknameWarning.isVisible()) {
            Main.changeMenu("ProfileMenu");

        }
    }

    public void changeToEdit() {
        Main.changeMenu("EditProfile");
    }


    @FXML
    public void chooseFromFiles(ActionEvent event) {

        try {
            // create a File chooser
            FileChooser fil_chooser = new FileChooser();
            FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");

            fil_chooser.getExtensionFilters().add(fileExtensions);
            File file = fil_chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

            if (file != null) {
                User activeUser = databaseController.activeUser();
                activeUser.setProfilePicture(file.getAbsolutePath());
                profilePic.setImage(new Image(new FileInputStream(file.getAbsolutePath())));

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void logOutButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LogOut");
        alert.setHeaderText("You're about to logout your account!");
        alert.setContentText("Are you sure you want to logout your account?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Main.changeMenu("LoginMenu");

        }
    }

    @FXML
    public void goToLeaderboard(ActionEvent event) throws IOException {
        Main.changeMenu("leaderboard");
    }

    @FXML
    public void goToGameMenu(ActionEvent event) throws IOException {
        Map map = databaseController.generateMapFromServer();
        databaseController.setCivilizations();
        Main.changeMenu("gameMap");
    }

    @FXML
    public void goToChatMenu(ActionEvent event) throws IOException {
        Main.changeMenu("GlobalChat");
    }
    public void EditMap(MouseEvent mouseEvent) {
        Map map = databaseController.generateMapFromServer();
        Main.changeMenu("EditMap");

    }

}
