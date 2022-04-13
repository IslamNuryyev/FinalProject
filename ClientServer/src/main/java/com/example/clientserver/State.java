package com.example.clientserver;

public class State {
    private static int player1location = -1;
    private static int player2location = -1;
    private static int player1guess = -1;
    private static int player2guess = -1;

    private static int max = 9;
    private static int round = 1;
    private static char user_inquiry;

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

    public static void setMax(int max) {
        State.max = max;
    }

    public static void setRound(int round) {
        State.round = round;
    }

    public static void setUser_inquiry(char user_inquiry) {
        State.user_inquiry = user_inquiry;
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

    public int getMax() {
        return max;
    }

    public int getRound() {
        return round;
    }

    public char getUser_inquiry() {
        return user_inquiry;
    }

}
