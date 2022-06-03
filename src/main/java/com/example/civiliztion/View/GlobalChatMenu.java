package com.example.civiliztion.View;

import com.example.civiliztion.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class GlobalChatMenu {

    private static GlobalChatMenu instance;
    public VBox firstBox;
    @FXML
    private Button changeMessageButton;
    @FXML
    private TextField editTextField;
    @FXML
    private VBox editMessage;
    @FXML
    private Button deleteForMe;
    @FXML
    private Button deleteForEveryone;
    @FXML
    private VBox deleteMessage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField text;
    @FXML
    private VBox allMessages;

    public static GlobalChatMenu getInstance(){
        if(instance == null){
            instance = new GlobalChatMenu();
        }
        return instance;
    }



    private User user;

    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }


    public void initialize(){
        allMessages.setSpacing(5);
    }





    public void send(MouseEvent mouseEvent)   {
        if(text.getText().length() == 0){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("empty text");
           alert.show();
        }else if(text.getText().length() > 0){
            Pane pane = getMessageAll();
            allMessages.getChildren().add(pane);
            text.clear();
        }
    }

    public void enter(KeyEvent keyEvent)  {
        if(keyEvent.getCode() == KeyCode.ENTER){
            keyEvent.consume();
            send(null);
        }
    }

    private Pane getMessageAll(){
        Pane pane = new Pane();
        pane.setPrefWidth(100);
        pane.setPrefHeight(80);
        pane.setStyle("-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;" +
                "-fx-background-color: #927819;");

        addUsersAndText(pane);

        return pane;
    }

    private void  addUsersAndText(Pane pane){

    }

    private void addUsersUsername(Pane pane){
        Label label = new Label("username: " + GlobalChatMenu.getInstance().getUser().getUsername());
        label.setPrefHeight(20);
        label.setLayoutX(30);
        label.setLayoutY(10);
        label.setFont(Font.font(12));
        label.setStyle("-fx-font-family: \"High Tower Text\"");
        pane.getChildren().add(label);
    }


    public void back(MouseEvent mouseEvent) {
    }
}
