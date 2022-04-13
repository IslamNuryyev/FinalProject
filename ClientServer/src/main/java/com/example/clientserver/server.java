package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class server {
    private static int player1location = -1;
    private static int player2location = -1;
    private int player1guess;
    private int player2guess;

    private int max = 9;
    private int round = 1;
    private char user_inquiry;

    private static class ClientHandler implements Runnable {

        private final Socket clientSock;

        public ClientHandler(Socket socket){
            clientSock = socket;
        }

        public void run(){
            BufferedReader inStream = null;
            try {
                inStream = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                String message;

                while ((message = inStream.readLine()) != null) {
                    if (player1location == -1) {
                        player1location = Integer.parseInt(message);
                    } else {
                        player2location = Integer.parseInt(message);
                    }

//
                System.out.println( "player1location = " + player1location);
                System.out.println( "player2location =  " + player2location);
//                System.out.println("firstInputted true = " + this.firstInputted);

//
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    inStream.close();
                    clientSock.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        ServerSocket serve = null;


        System.out.println("----------------------------------");
        System.out.println("Welcome to Battleship Lite!");
        System.out.println("----------------------------------\n");


        try {
            serve = new ServerSocket(3000); //0 -> lets your OS select a port; port > 1024
            serve.setReuseAddress(true);
            System.out.println("Starting server...");
            System.out.println("Waiting for client connection...");
            while(true){
                Socket sock = serve.accept();
                System.out.println("Client is connected " + sock.getInetAddress().getHostAddress()); //this will display the host address of client
                ClientHandler client = new ClientHandler(sock);
                new Thread(client).start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try {
            serve.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}