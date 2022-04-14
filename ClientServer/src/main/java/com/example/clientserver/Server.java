package com.example.clientserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Hosts a game of console Battleship on a Server.
 */
public class Server {
    /**
     * Reads Client inputs and handles game data.
     */

    //
    static Scanner scanner = new Scanner(System.in);
    static PrintStream standard = System.out;
    static PrintStream out;
    //

    private static class ClientHandler implements Runnable {

        private final Socket clientSock;
        /**
         * Creates socket for connecting clients
         * @param socket client location
         */
        public ClientHandler(Socket socket){
            clientSock = socket;
        }

        /**
         * Takes client inputs and manipulates the Battleship game.
         */
        public void run(){
            BufferedReader inStream = null;
            try {
                inStream = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                String message;

                while ((message = inStream.readLine()) != null) {
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
                    } else {
                        if (State.getPlayer1guess() != -1 && State.getIsPlayer1Move()) {
                            State.setPlayer1guess(-1);
                            State.setIsPlayer1Move(false);
                            System.out.println("Confirm your guess");
                        } else if (State.getPlayer2guess() != -1) {
                            State.setPlayer2guess(-1);
                            State.setIsPlayer1Move(true);
                            System.out.println("Confirm your guess");
                        }
                    }


                    if (State.getPlayer2location() == State.getPlayer1guess() && State.getPlayer2location() != -1) {
                        //
                        System.out.println("HIT! Player 1 won.");
                        out.close();
                        System.setOut(standard);
                        System.out.println("HIT! Player 1 won.");
                        //
                        break;
                    }

                    if (State.getPlayer1location() == State.getPlayer2guess() && State.getPlayer1location() != -1) {
                        //
                        System.out.println("HIT! Player 2 won.");
                        out.close();
                        System.setOut(standard);
                        System.out.println("HIT! Player 2 won.");//
                        //
                        break;
                    }

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

    /**
     * Starts up Server on localhost
     * Function is always looking for new clients, meaning more than the two clients can
     * connect but the game will not accommodate more than two clients
     */
    public static void main(String[] args){
        ServerSocket serve = null;

        //
        System.out.print("Enter new Game Log file name: ");
        String fileName = scanner.nextLine();
        //

        try {
            serve = new ServerSocket(3000); //0 -> lets your OS select a port; port > 1024
            serve.setReuseAddress(true);

            //
            out = new PrintStream(new FileOutputStream(fileName+".txt"));
            //

            System.out.println("Starting server...");
            System.out.println("Waiting for client connection...");

            while(true){
                //
                System.setOut(out);
                //
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
