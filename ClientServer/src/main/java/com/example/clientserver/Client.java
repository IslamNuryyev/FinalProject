package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Connects a Client to a Server to play a game of Battleship
 * The client requires a running Server on localhost to work.
 */
public class Client {
    public static void main(String args[]) {
       
        try (Socket sock = new Socket("localhost", 3000)){

            System.out.println("Connected to server...");
            System.out.println("Input \"done\" to terminate connection...");

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

            while(!message.equals("done")){
                System.out.println("Guess a location of the battleship [0,9]: ");
                message = scanner.nextLine();
                System.out.println("message = " + message);
                dout.println(message);
            }
        
            scanner.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Connection terminated...");

    }
}
