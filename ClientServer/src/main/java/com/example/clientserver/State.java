package com.example.clientserver;

/**
 * Used for holding game variables
 */
public class State {
    private static int player1location = -1;
    private static int player2location = -1;
    private static int player1guess = -1;
    private static int player2guess = -1;

    private static Boolean isPlayer1Move = true;


    public static Boolean getIsPlayer1Move() {
        return isPlayer1Move;
    }

    public static void setIsPlayer1Move(Boolean player1Move) {
        State.isPlayer1Move = player1Move;
    }


    public static void setPlayer1location(int player1location) {
        State.player1location = player1location;
    }

    public static void setPlayer2location(int player2location) {
        State.player2location = player2location;
    }

    public static void setPlayer1guess(int player1guess) {
        State.player1guess = player1guess;
    }

    public static void setPlayer2guess(int player2guess) {
        State.player2guess = player2guess;
    }

    public static int getPlayer1location() {
        return player1location;
    }

    public static int getPlayer2location() {
        return player2location;
    }

    public static int getPlayer1guess() {
        return player1guess;
    }

    public static int getPlayer2guess() {
        return player2guess;
    }
}
