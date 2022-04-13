package com.example.clientserver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Battleship extends Application {

    private Scene menu, startSc, exitSc; // startSc and exitSc not used (yet)

    @Override
    public void start(Stage primaryStage) throws IOException {
        Label homeSign = new Label("Main Menu");
        Button startButt = new Button("Start");
        Button exitButt = new Button("Exit");


        startButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new TestBS().start(new Stage());
            } // opens new window, not sure if this is what we want
        });
        exitButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        VBox mainMenu = new VBox(20); // small main menu size
        mainMenu.setAlignment(Pos.CENTER); // simplify main menu by centering javafx items
        mainMenu.getChildren().addAll(homeSign,startButt,exitButt); // include labels and buttons in main menu
        menu = new Scene(mainMenu, 320,240);
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
