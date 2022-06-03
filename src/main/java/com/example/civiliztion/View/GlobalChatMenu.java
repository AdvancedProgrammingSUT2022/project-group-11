package com.example.civiliztion.View;

import com.example.civiliztion.Model.Clock;
import com.example.civiliztion.Model.GlobalChats.Message;
import com.example.civiliztion.Model.GlobalChats.publicChat;
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
        scrollPane.vvalueProperty().bind(allMessages.heightProperty());
        allMessages.setSpacing(5);
        for (int i = 0; i < publicChat.getInstance().getAllPublicMessage().size(); i++) {
            Message message = publicChat.getInstance().getAllPublicMessage().get(i);
            if (!(message.getUser() == user && message.getHasDelete()))
                sendNewMessage(message);
            if (message.getUser() != user)
                message.setHasSeen(true);
        }
    }
    public void enter(KeyEvent keyEvent)  {
        if(keyEvent.getCode() == KeyCode.ENTER){
            keyEvent.consume();
            send(null);
        }
    }

    public void back(MouseEvent mouseEvent) {
    }


    public void send(MouseEvent mouseEvent)   {
        if(text.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("empty text");
            alert.show();
        }else if(text.getText().length() > 20){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("long message");
            alert.show();
        }else{
           Message message = new Message(user,text.getText(), Clock.getTime());
           publicChat.getInstance().addMessage(message);
           sendNewMessage(message);
           text.clear();
        }
    }

    private void sendNewMessage(Message message) {
        Pane pane = getMessageBox(message);
        allMessages.getChildren().add(pane);
    }
    private Pane getMessageBox(Message message)  {
        Pane pane = new Pane();
        pane.setPrefWidth(100);
        pane.setPrefHeight(80);
        pane.setStyle("-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;" +
                "-fx-background-color: #927819;");
       userAvatars(pane,message.getUser());
       addText(pane,message.getOwnMessage());
       addUsersUsername(pane,message.getUser());
        addClock(pane, message.getClock());
        return pane;
    }


    public void userAvatars(Pane pane,User user){


    }

    public void addText(Pane pane, String message){
        Label text = new Label(message);
        text.setPrefHeight(20);
        text.setPrefHeight(60);
        text.setLayoutX(40);
        text.setLayoutY(15);
        text.setFont(Font.font(18));
        pane.getChildren().add(text);
    }



    public void addUsersUsername(Pane pane,User user){
        Label label = new Label("player: " + user.getUsername());
        label.setPrefHeight(20);
        label.setLayoutX(40);
        label.setLayoutY(10);
        label.setFont(Font.font(12));
        label.setStyle("-fx-font-family: \"High Tower Text\"");
        pane.getChildren().add(label);
    }

    public void addClock(Pane pane, String stringClock) {
        Label clock = new Label(stringClock);
        clock.setPrefHeight(60);
        clock.setPrefWidth(60);
        clock.setLayoutX(260);
        clock.setLayoutY(25);
        pane.getChildren().add(clock);
    }



}
