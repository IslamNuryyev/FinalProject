package com.example.clientserver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Battleship extends Application {

    private Scene menu, startSc, exitSc;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Label homeSign = new Label("Main Menu");
        Button startButt = new Button("Start");
        Button exitButt = new Button("Exit");
        startButt.setOnAction(event -> primaryStage.setScene(startSc));
        exitButt.setOnAction(event -> primaryStage.setScene(exitSc));



        VBox mainMenu = new VBox(20);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.getChildren().addAll(homeSign,startButt,exitButt);
        menu = new Scene(mainMenu, 320,240);
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
