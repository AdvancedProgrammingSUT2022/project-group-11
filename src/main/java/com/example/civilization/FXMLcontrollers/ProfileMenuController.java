package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileMenuController
{
    public static DatabaseController databaseController;
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
    
    private int number = Main.database.getActiveUser().photoNumber;


    public void setTexts() throws FileNotFoundException {
        username.setText(Main.database.getActiveUser().getUsername());
        nickname.setText(Main.database.getActiveUser().getNickname());
        Image image = new Image(new FileInputStream(Main.database.getActiveUser().getProfilePicture()));
        //Image image = new Image( ProfileMenuController.class.getResource("/com/example/civilization/PNG/prof" + Main.database.getActiveUser().photoNumber + ".png").toString());
        photo.setImage(image);


    }

    public void setUpEditPage()
    {
        editNickname.setPromptText(Main.database.getActiveUser().getNickname());
        editPassword.setPromptText(Main.database.getActiveUser().getPassword());
        Image image = null;
        try {
            image = new Image(new FileInputStream(Main.database.getActiveUser().getProfilePicture()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Image image = new Image(ProfileMenuController.class.getResource("/com/example/civilization/PNG/prof" + Main.database.getActiveUser().photoNumber + ".png").toString());
        profilePic.setImage(image);


    }

    public void changePicTo1()
    {
        profilePic.setImage(pic1.getImage());
        number = 1;
    }

    public void changePicTo2()
    {
        profilePic.setImage(pic2.getImage());
        number = 2;
    }

    public void changePicTo3()
    {
        profilePic.setImage(pic3.getImage());
        number = 3;

    }

    public void changePicTo4()
    {
        profilePic.setImage(pic4.getImage());
        number = 4;
    }

    public void changePicTo5()
    {
        profilePic.setImage(pic5.getImage());
        number = 5;
    }

    public void changePicTo6()
    {
        profilePic.setImage(pic6.getImage());
        number = 6;
    }

    public void changePicTo7()
    {
        profilePic.setImage(pic7.getImage());
        number = 7;
    }

    public void changePicTo8()
    {
        profilePic.setImage(pic8.getImage());
        number = 8;
    }

    public void saveChanges()
    {
        String newNickname = editNickname.getText();
        String newPassword = editPassword.getText();
        if ( !newNickname.equals(""))
        {
            String temp = databaseController.changeUserNickname(newNickname, Main.database.getActiveUser());
            if ( temp.startsWith("user"))
            {
                nicknameWarning.setText(temp);
                nicknameWarning.setVisible(true);
            }
            else
            {
                nicknameWarning.setVisible(false);
            }
        }
        if ( !newPassword.equals(""))
        {
            String passTemp = databaseController.changePassword(newPassword, Main.database.getActiveUser());
            if ( passTemp.startsWith("please"))
            {
                passwordWarning.setText(passTemp);
                passwordWarning.setVisible(true);

            }
            else
            {
                passwordWarning.setVisible(false);
            }

        }
        if ( !passwordWarning.isVisible() && !nicknameWarning.isVisible() )
        {
            Main.database.getActiveUser().photoNumber = number;
            Main.changeMenu("ProfileMenu");

        }
    }

    public void changeToEdit()
    {
        Main.changeMenu("EditProfile");
    }


    @FXML
    public void chooseFromFiles(ActionEvent event) {

        try {
            // create a File chooser
            FileChooser fil_chooser = new FileChooser();
            FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");

            fil_chooser.getExtensionFilters().add(fileExtensions);
            File file = fil_chooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

            if (file != null) {

                Main.database.getActiveUser().setProfilePicture(file.getAbsolutePath());
                profilePic.setImage(new Image(new FileInputStream(file.getAbsolutePath())));

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


}
