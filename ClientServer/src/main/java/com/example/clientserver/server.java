package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.*;

public class server {
    private static int player1location = -1;
    private static int player2location = -1;
    private static int player1guess;
    private static int player2guess;

    private static int max = 9;
    private static int round = 1;
    private static char user_inquiry;

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
//                message = inStream.readLine();
//                player1location = Integer.parseInt(message);
//
//                message = inStream.readLine();
//                player2location = Integer.parseInt(message);
//
//                System.out.println( "player1location = " + player1location);
//                System.out.println( "player2location =  " + player2location);


                while ((message = inStream.readLine()) != null) {
                    if (player1location == -1) {
                        player1location = Integer.parseInt(message);
                        System.out.println( "Player 1 has selected its' location");

                    } else if (player2location == -1) {
                        player2location = Integer.parseInt(message);
                        System.out.println( "Player 2 has selected its' location");
                        break;
                    }
                }

//                for development
                System.out.println( "player1location = " + player1location);
                System.out.println( "player2location =  " + player2location);
//                for development


                System.out.println("Ready to begin ");
                System.out.println("Round " + round);





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