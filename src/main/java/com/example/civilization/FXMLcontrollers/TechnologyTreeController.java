package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Model.Technologies.TechnologyTypes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class TechnologyTreeController {
    @FXML
    ScrollPane scrollPane;
    @FXML
    AnchorPane anchorPane;


    @FXML
    public void initialize() throws IOException {
        DatabaseController.getInstance().getMap().generateMap();
        DatabaseController.getInstance().setCivilizations(DatabaseController.getInstance().getDatabase().getUsers());
        DatabaseController.getInstance().getMap().initializeMapUser(DatabaseController.getInstance().getDatabase().getActiveUser());

        Platform.runLater(this::setTechnologyButton);
        for (TechnologyTypes technologyTypes : TechnologyTypes.values())
            System.out.println(technologyTypes.name() + " " + DatabaseController.getInstance().getFirstRequiredTechnology(DatabaseController.getInstance().getDatabase().getActiveUser(), technologyTypes).name());


        /*
        try {
            scrollPane.setContent(new ImageView(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/Background/leaderboard.jpg"))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

         */
    }

    public void setTechnologyButton() {
        for (Node children : anchorPane.getChildren()) {
            if (children instanceof StackPane) {
                if (((StackPane) children).getChildren().get(1) instanceof Text && ((StackPane) children).getChildren().get(0) instanceof Rectangle) {
                    ((Text) ((StackPane) children).getChildren().get(1)).setFont(Font.font("Copperplate", 18));
                    /// System.out.println(((Text)((StackPane) children).getChildren().get(1)).getText());
                    System.out.println(DatabaseController.getInstance().getTechnologyTypeByName(((Text) ((StackPane) children).getChildren().get(1)).getText()).name());
                    setColor((StackPane) children);

                    children.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            if (mouseEvent.getClickCount() == 2) {
                                DatabaseController.getInstance().choosingATechnologyToStudyForGraphic(DatabaseController.getInstance().getDatabase().getActiveUser(),DatabaseController.getInstance().getTechnologyTypeByName(((Text) ((StackPane) children).getChildren().get(1)).getText()));
                                setIcons();
                            }
                        }
                    });

                }





            }
        }

        for (Node children : anchorPane.getChildren()) {
            if (children instanceof Line) {
                ((Line) children).setStrokeWidth(3);

            }
        }

    }

    private void setColor(StackPane children) {
        if (DatabaseController.getInstance().getTechnologyByTechnologyType(DatabaseController.getInstance().getDatabase().getActiveUser(), DatabaseController.getInstance().getTechnologyTypeByName(((Text) children.getChildren().get(1)).getText())) != null && DatabaseController.getInstance().getTechnologyByTechnologyType(DatabaseController.getInstance().getDatabase().getActiveUser(), DatabaseController.getInstance().getTechnologyTypeByName(((Text) children.getChildren().get(1)).getText())).getIsAvailable()) {
            ((Rectangle) children.getChildren().get(0)).setFill(Color.DEEPSKYBLUE);
        } else if (DatabaseController.getInstance().getTechnologyByTechnologyType(DatabaseController.getInstance().getDatabase().getActiveUser(), DatabaseController.getInstance().getTechnologyTypeByName(((Text) children.getChildren().get(1)).getText())) != null && DatabaseController.getInstance().getTechnologyByTechnologyType(DatabaseController.getInstance().getDatabase().getActiveUser(), DatabaseController.getInstance().getTechnologyTypeByName(((Text) children.getChildren().get(1)).getText())).getUnderResearch()) {
            ((Rectangle) children.getChildren().get(0)).setFill(Color.YELLOW);
        }else{
            ((Rectangle) children.getChildren().get(0)).setFill(Color.WHITE);
        }
    }

    public void setIcons(){
        for (Node children : anchorPane.getChildren()) {
            if (children instanceof StackPane) {
                if (((StackPane) children).getChildren().get(1) instanceof Text && ((StackPane) children).getChildren().get(0) instanceof Rectangle) {
                    ((Text) ((StackPane) children).getChildren().get(1)).setFont(Font.font("Copperplate", 18));

                    setColor((StackPane) children);

                }

            }
        }
    }
}
