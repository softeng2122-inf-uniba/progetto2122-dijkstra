package it.uniba.app;

import java.util.Scanner;

public class Giocatore {
    public String input() {
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();
        return userInput;
    }
    public Giocatore(){
    }
}