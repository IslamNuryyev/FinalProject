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

    private int p1hit = -1;
    private int p2hit = -1;

    private Scene menu, fullBoard, winScene;
    private Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //The plan was to get the data that was inputted from Client.java into the Server and stored in State.java
        //and use it here in UI. However, for some reason when we access data from State.java it returns default values
        //and data does not preserve
        // System.out.println(State.getPlayer1location()); //outputs -1, not the inputted location from a player

        stage = primaryStage;
        Label instructions = new Label("Enter an integer between 0 and "+String.valueOf(numPlaces-1));
        Button confirm = new Button("Confirm"); // for confirming battleship location
        TextField inputp1 = new TextField(); // for inputting battleship location (player 1)
        TextField inputp2 = new TextField(); // for inputting battleship location (player 2)
        inputp1.setPromptText("Player 1");
        inputp2.setPromptText("Player 2");


        inputp1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputp1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        inputp2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputp2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        confirm.setOnAction(event -> { // find the battleship
//            System.out.println(inputp1.getText());
            int spot = Integer.parseInt(inputp1.getText());
            int spotp2 = Integer.parseInt(inputp2.getText());
            if ((spot >= 0 && spot <= numPlaces-1) && spotp2 >= 0 && spotp2 <= numPlaces-1) {
//                System.out.println("State.getPlayer1location() = " + State.getPlayer1location());
//                p1hit = State.getPlayer1location();
                p1hit = spot;
                p2hit = spotp2;
                stage.setScene(fullBoard);
                stage.setTitle("Battleship Lite");
            }
            inputp1.setText("");
            inputp2.setText("");
            inputp1.setPromptText("Try Again (player 1)");
            inputp2.setPromptText("Try Again (player 2)");
        });




        VBox pickLocation = new VBox(10);
        pickLocation.getChildren().addAll(instructions,inputp1,inputp2,confirm); // add battleship setup items to VBox (to pick start location)

        menu = new Scene(pickLocation, (numPlaces+1)*placeWidth,boardHeight*3); // make scene wide enough for game and add pickLocation items


        Group board1 = new Group(buildBoard(true),getShips(true),buildBoard(false),getShips(false)); // put game board and ship location in same group

        fullBoard = new Scene(board1, (numPlaces+1)*placeWidth,boardHeight*3); // display playable game board

        // can be used later for rematch, quit to main menu, exit to desktop?
//        // Not Really Needed
//        Label winText = new Label("Good Sp1hit Bro");
//        Button backToMenu = new Button("Play Again");
//        backToMenu.setOnAction(event -> stage.setScene(menu));
//        VBox win = new VBox(winText, backToMenu);
//        winScene = new Scene(win, (numPlaces+1)*placeWidth,boardHeight);


        stage.setScene(menu);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public Group buildBoard(boolean p1) {
        double height;
        if(p1) {
            height = 0;
        } else {
            height = 2;
        }

        Group startBoard = new Group();

        for (int ii = 2; ii < numPlaces+1; ii++) {
            Line newLine = new Line();
            newLine.setStartX(ii*placeWidth);
            newLine.setStartY(0+height*boardHeight);
            newLine.setEndX(ii*placeWidth);
            newLine.setEndY(0+boardHeight+height*boardHeight);

            startBoard.getChildren().add(newLine);
//            System.out.println(p1hit);
        }


        return startBoard;
    }

    public Group getShips(boolean p1) {
        double height;
        if(p1) {
            height = 0;
        } else {
            height = 2;
        }


        Group ships = new Group();

        for (int ii = 1; ii < numPlaces+1; ii++) {
            Circle ship = new Circle((boardHeight/2)*0.8); // create clickable ship
            ship.setFill(Color.GREY); // blank colour to indicate unselected spot during the game
            ship.setCenterX((ii*placeWidth)+placeWidth*0.5); // place ships in a line along the x-axis
            ship.setCenterY(boardHeight*0.5+height*boardHeight);
            ship.setId(String.valueOf(ii-1)); // give ship ID to match ship location

            Label placeID = new Label(String.valueOf(ii-1));
            placeID.setLayoutX((ii*placeWidth)+placeWidth*0.1);
            placeID.setLayoutY(boardHeight*0.1+height*boardHeight);

            if (p1) {
                ship.setOnMouseClicked(event -> {
//                System.out.println(ship.getId());
                    if (Integer.parseInt(ship.getId()) == p2hit) {
                        // Game is over at this point.
                        ship.setFill(Color.GREEN);
                        stage.setTitle("Player 1 Wins!");
//                    stage.setScene(winScene);
                    } else {
                        ship.setFill(Color.RED);
                    }
                });
            } else {
                ship.setOnMouseClicked(event -> {
//                System.out.println(ship.getId());
                    if (Integer.parseInt(ship.getId()) == p1hit) {
                        // Game is over at this point.
                        ship.setFill(Color.GREEN);
                        stage.setTitle("Player 2 Wins!");
//                    stage.setScene(winScene);
                    } else {
                        ship.setFill(Color.RED);
                    }
                });
            }

            ships.getChildren().addAll(ship,placeID);
        }

        return ships;
    }
}
