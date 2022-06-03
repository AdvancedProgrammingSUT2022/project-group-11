package com.example.civiliztion.View;

import com.example.civiliztion.Model.User;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class GlobalChatMenu {

    private static GlobalChatMenu instance;
    @FXML
    private VBox allMessages;

    public static GlobalChatMenu getInstance(){
        if(instance == null){
            instance = new GlobalChatMenu();
        }
        return instance;
    }

    private User user;



    
}
