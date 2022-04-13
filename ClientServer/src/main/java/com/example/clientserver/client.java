package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class client{

    public static void main(String args[]){

        server gameState = new server();

        try (Socket sock = new Socket("localhost", 3000)){
            System.out.println("gameState.player1location = " + gameState.getPlayer1location());
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

            System.out.println("Guess a location of the battleship [0,9]: ");

            message = scanner.nextLine();
            dout.println(message);



            scanner.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Connection terminated...");

    }
}