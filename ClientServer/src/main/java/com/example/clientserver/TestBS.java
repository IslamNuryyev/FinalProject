package com.example.clientserver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class TestBS extends Application {
    private int numPlaces = 10;

    // These should be the same value.
    private double placeWidth = 100;
    private double boardHeight = 100;

    private int hit = -1;

    private Scene menu, fullBoard, winScene;
    private Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Label instructions = new Label("Enter an integer between 0 and "+String.valueOf(numPlaces-1));
        Button confirm = new Button("Confirm"); // for confirming battleship location
        TextField input = new TextField(); // for inputting battleship location


        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    input.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        confirm.setOnAction(event -> { // find the battleship
            System.out.println(input.getText());
            int spot = Integer.parseInt(input.getText());
            if (spot >= 0 && spot <= numPlaces-1) {
                hit = spot;
                stage.setScene(fullBoard);
                stage.setTitle("Battleship Lite");
            }
            input.setText("");
            input.setPromptText("Try Again");
        });


        VBox pickLocation = new VBox(10);
        pickLocation.getChildren().addAll(instructions,input,confirm); // add battleship setup items to VBox (to pick start location)

        menu = new Scene(pickLocation, (numPlaces+1)*placeWidth,boardHeight); // make scene wide enough for game and add pickLocation items


        Group board = new Group(buildBoard(),getShips()); // put game board and ship location in same group

        fullBoard = new Scene(board, (numPlaces+1)*placeWidth,boardHeight); // display playable game board

        // can be used later for rematch, quit to main menu, exit to desktop?
//        // Not Really Needed
//        Label winText = new Label("Good Shit Bro");
//        Button backToMenu = new Button("Play Again");
//        backToMenu.setOnAction(event -> stage.setScene(menu));
//        VBox win = new VBox(winText, backToMenu);
//        winScene = new Scene(win, (numPlaces+1)*placeWidth,boardHeight);


        stage.setScene(menu);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public Group buildBoard() {
        Group startBoard = new Group();

        for (int ii = 2; ii < numPlaces+1; ii++) {
            Line newLine = new Line();
            newLine.setStartX(ii*placeWidth);
            newLine.setStartY(0);
            newLine.setEndX(ii*placeWidth);
            newLine.setEndY(0+boardHeight);

            startBoard.getChildren().add(newLine);
//            System.out.println(hit);
        }


        return startBoard;
    }

    public Group getShips() {
        Group ships = new Group();

        for (int ii = 1; ii < numPlaces+1; ii++) {
            Circle ship = new Circle((boardHeight/2)*0.8); // create clickable ship
            ship.setFill(Color.GREY); // blank colour to indicate unselected spot during the game
            ship.setCenterX((ii*placeWidth)+placeWidth*0.5); // place ships in a line along the x-axis
            ship.setCenterY(boardHeight*0.5);
            ship.setId(String.valueOf(ii-1)); // give ship ID to match ship location

            Label placeID = new Label(String.valueOf(ii-1));
            placeID.setLayoutX((ii*placeWidth)+placeWidth*0.1);
            placeID.setLayoutY(boardHeight*0.1);

            ship.setOnMouseClicked(event -> {
                System.out.println(ship.getId());
                if(Integer.parseInt(ship.getId()) == hit) {
                    // Game is over at this point.
                    ship.setFill(Color.GREEN);
//                    stage.setScene(winScene);
                } else {
                    ship.setFill(Color.RED);
                }
            });

            ships.getChildren().addAll(ship,placeID);
        }

        return ships;
    }
}
