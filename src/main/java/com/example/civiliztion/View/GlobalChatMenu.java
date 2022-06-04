package com.example.civiliztion.View;

import com.example.civiliztion.Main;
import com.example.civiliztion.Model.Clock;
import com.example.civiliztion.Model.GlobalChats.Message;
import com.example.civiliztion.Model.GlobalChats.publicChat;
import com.example.civiliztion.Model.User;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;


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


    public void initialize() throws MalformedURLException {
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
    public void enter(KeyEvent keyEvent) throws MalformedURLException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            keyEvent.consume();
            send(null);
        }
    }

    public void back(MouseEvent mouseEvent) {
    }


    public void send(MouseEvent mouseEvent) throws MalformedURLException {
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

    private void sendNewMessage(Message message) throws MalformedURLException {
        Pane pane = getMessageBox(message);
        allMessages.getChildren().add(pane);
    }
    private Pane getMessageBox(Message message) throws MalformedURLException {
        Pane pane = new Pane();
        pane.setPrefWidth(100);
        pane.setPrefHeight(80);
        pane.setStyle("-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;" +
                "-fx-background-color: #927819;");
       userAvatars(pane,message.getUser());
       addText(pane,message.getOwnMessage());
       addUsersUsername(pane,message.getUser());
        addClock(pane, message.getClockTime());
        if (message.getUser() == user) {
            addSeenUnSeen(pane, message.getHasSeen());
        }
        if (message.getUser() == user) {
            addButtonToDelete(pane,message);
        }
        if (message.getUser() == user) {
            addButtonToEdit(pane,message);
        }

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


    private void addSeenUnSeen(Pane pane, boolean isSeen) throws MalformedURLException {
        ImageView imageView;
        if (isSeen) {
            imageView = new ImageView(new Image(String.valueOf(
                    new URL(Main.class.getResource("images/seen.png").toString()))));
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            imageView.setLayoutX(305);
            imageView.setLayoutY(45);
        } else {
            imageView = new ImageView(new Image(String.valueOf(
                    new URL(Main.class.getResource("images/unseen.png").toString()))));
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            imageView.setLayoutX(300);
            imageView.setLayoutY(50);
        }
        pane.getChildren().add(imageView);
    }

    public void addButtonToEdit(Pane pane, Message message) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(Main.class.getResource("images/edit.png").toString()))));
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setLayoutX(288);
        imageView.setLayoutY(14);
        imageView.setCursor(Cursor.HAND);
        AtomicBoolean isSelectedForEdit = new AtomicBoolean(true);
        imageView.setOnMouseClicked(mouseEvent -> {
            if (isSelectedForEdit.get()) {
                isSelectedForEdit.set(false);
                pane.getChildren().add(editMessage);
                editMessage.setLayoutX(330);
                editMessage.setLayoutY(0);
                editTextField.setText(message.getOwnMessage());
                editMessageClicked(pane, isSelectedForEdit, message);
                editMessage.setVisible(true);
                editTextField.requestFocus();
            } else {
                isSelectedForEdit.set(true);
                pane.getChildren().remove(editMessage);
            }
        });
        pane.getChildren().add(imageView);
    }


    public void editMessageClicked(Pane pane, AtomicBoolean isSelectedForEdit, Message message) {
        editTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                if (editTextField.getText().length() > 20) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("long message");
                    alert.show();
                }
                else {
                    isSelectedForEdit.set(true);
                    if (pane.getChildren().get(1) instanceof Label) {
                        ((Label) pane.getChildren().get(1)).setText(editTextField.getText());
                    }
                    message.setOwnMessage(editTextField.getText());
                    pane.getChildren().remove(editMessage);
                }
            }
        });
        changeMessageButton.setOnMouseClicked(mouseEvent -> {
            if (editTextField.getText().length() > 20) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("long message");
                alert.show();
            }
            else {
                isSelectedForEdit.set(true);
                if (pane.getChildren().get(1) instanceof Label) {
                    ((Label) pane.getChildren().get(1)).setText(editTextField.getText());
                }
                message.setOwnMessage(editTextField.getText());
                pane.getChildren().remove(editMessage);
            }
        });
    }


    public void addButtonToDelete(Pane pane, Message message) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(Main.class.getResource("images/blackCross.png").toString()))));
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setLayoutX(310);
        imageView.setLayoutY(15);
        imageView.setCursor(Cursor.HAND);
        AtomicBoolean isSelectedForDelete = new AtomicBoolean(true);
        imageView.setOnMouseClicked(mouseEvent -> {
            if (isSelectedForDelete.get()) {
                isSelectedForDelete.set(false);
                pane.getChildren().add(deleteMessage);
                deleteMessage.setLayoutX(330);
                deleteMessage.setLayoutY(0);
                deleteMessageClicked(pane, isSelectedForDelete, message);
                deleteMessage.setVisible(true);
            } else {
                isSelectedForDelete.set(true);
                pane.getChildren().remove(deleteMessage);
            }
        });
        pane.getChildren().add(imageView);
    }

    public void deleteMessageClicked(Pane pane, AtomicBoolean isSelectedForDelete, Message message) {
        deleteForEveryone.setOnMouseClicked(mouseEvent -> {
            isSelectedForDelete.set(true);
            pane.getChildren().remove(deleteMessage);
            allMessages.getChildren().remove(pane);
            publicChat.getInstance().getAllPublicMessage().remove(message);
        });
        deleteForMe.setOnMouseClicked(mouseEvent -> {
            isSelectedForDelete.set(true);
            pane.getChildren().remove(deleteMessage);
            allMessages.getChildren().remove(pane);
            message.setHasDelete(true);
        });
    }




}
