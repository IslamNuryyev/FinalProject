package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String args[]) {
       
        try (Socket sock = new Socket("localhost", 3000)){

            System.out.println("Connected to server...");
            System.out.println("Input \"you are done\" to terminate connection...");

            System.out.println("----------------------------------");
            System.out.println("Welcome to Battleship Lite!");
            System.out.println("----------------------------------\n");

            System.out.println("Select the location of your battleship [0,9]: ");
            //get input from the user to send as a message
            PrintWriter dout = new PrintWriter(sock.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String message = "";
            message = scanner.nextLine();
            dout.println(message);

            while(!message.equals("you are done")){
                System.out.println("Guess a location of the battleship [0,9]: ");
                message = scanner.nextLine();
                System.out.println("message = " + message);
                dout.println(message);
            }

//            System.out.println("Server.getPlayer1location() = " + State.getPlayer1location());
//
//            if (State.getPlayer1location() == -1) {
//                State.setPlayer1location(Integer.parseInt(message));
//            } else if (State.getPlayer2location() == -1) {
//                State.setPlayer2location(Integer.parseInt(message));
//            }
//
//            System.out.println("gameState.player1location = " + State.getPlayer1location());
//            System.out.println("gameState.player2location = " + State.getPlayer2location());




            // do {
            //     System.out.println("Guess a location of the battleship [0,9]: ");

            //     message = scanner.nextLine();
            //     dout.println(message);

            //     System.out.println("gameState.getPlayer1guess() = " + Server.getPlayer1guess());
            //     System.out.println("gameState.getPlayer2location() = " + Server.getPlayer2location());

            //     if (Server.getPlayer2location() == Server.getPlayer1guess()) {
            //         System.out.println("HIT! Player 1 won.");
            //         break;
            //     }

            //     if (Server.getPlayer1location() == Server.getPlayer2guess()) {
            //         System.out.println("HIT! Player 2 won.");
            //         break;
            //     }

            // }  while (Server.getPlayer2guess() != Server.getPlayer1location() || Server.getPlayer2guess() != Server.getPlayer1location());

        
            scanner.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Connection terminated...");

    }
}