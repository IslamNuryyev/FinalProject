package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class client{

    public static void main(String args[]){

        try (Socket sock = new Socket("localhost", 3000)){
            System.out.println("Connected to server...");
            System.out.println("Input \"you are done\" to terminate connection...");
            //get input from the user to send as a message
            PrintWriter dout = new PrintWriter(sock.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String message = "";

            while(!message.equals("you are done")){
                message = scanner.nextLine();
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