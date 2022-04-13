package com.example.clientserver;

import java.net.*;
import java.io.*;

public class Server {
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
//                    if (State.getPlayer1guess() != -1) {
//                        State.setPlayer1guess(-1);
//                    } else if (State.getPlayer2guess() != -1) {
//                        State.setPlayer2guess(-1);
//                    }

                    if (State.getPlayer1location() == -1) {
                        State.setPlayer1location(Integer.parseInt(message));
                        System.out.println( "Player 1 has selected its' location");
                    } else if (State.getPlayer2location() == -1) {
                        State.setPlayer2location(Integer.parseInt(message));
                        System.out.println( "Player 2 has selected its' location");
                    } else if (State.getPlayer1guess() == -1) {
                       State.setPlayer1guess(Integer.parseInt(message));
                       System.out.println("player1guess = " + State.getPlayer1guess());
                    } else if (State.getPlayer2guess() == -1) {
                        State.setPlayer2guess(Integer.parseInt(message));
                        System.out.println("player2guess = " + State.getPlayer2guess());
                    }

                    //                for development
                    System.out.println( "State.getPlayer1location() = " + State.getPlayer1location());
                    System.out.println( "State.getPlayer2location() = " + State.getPlayer2location());
                    //                for development


                    if (State.getPlayer2location() == State.getPlayer1guess() && State.getPlayer2location() != -1) {
                         System.out.println("HIT! Player 1 won.");
                         break;
                    }

                    if (State.getPlayer1location() == State.getPlayer2guess() && State.getPlayer1location() != -1) {
                        System.out.println("HIT! Player 2 won.");
                        break;
                    }

//


//                    do {
//                            while ((message = inStream.readLine()) != null) {
//                                if (State.getPlayer1guess() == -1) {
//                                    State.setPlayer1guess(Integer.parseInt(message));
//                                    System.out.println("player1guess = " + State.getPlayer1guess());
//
//                                } else if (State.getPlayer2guess() == -1) {
//                                    State.setPlayer2guess(Integer.parseInt(message));
//                                    System.out.println("player2guess = " + State.getPlayer2guess());
//                                }
//                            }
//
//
////                        while ((message = inStream.readLine()) != null) {
////                            State.setPlayer1guess(Integer.parseInt(message));
////                            System.out.println("player1guess = " + State.getPlayer1guess());
////                            break;
////                        }
////
////
////                        while ((message = inStream.readLine()) != null) {
////                            State.setPlayer2guess(Integer.parseInt(message));
////                            System.out.println("player2guess = " + State.getPlayer2guess());
////                            break;
////                        }
//
//
////                        if (State.getPlayer2location() == State.getPlayer1guess()) {
////                            System.out.println("HIT! Player 1 won.");
////                        }
////
////                        if (State.getPlayer1location() == State.getPlayer2guess()) {
////                            System.out.println("HIT! Player 2 won.");
////                        }
//                        } while (State.getPlayer2guess() != State.getPlayer1location() || State.getPlayer1guess() != State.getPlayer2location());




                }


                
                


//                for development
//                System.out.println( "player1guess = " + player1guess);
//                System.out.println( "player2guess =  " + player2guess);
//                for development



                // System.out.println("Ready to begin ");
                // System.out.println("Round " + round);



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