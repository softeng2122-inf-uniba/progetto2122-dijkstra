package it.uniba.app;

import java.util.Scanner;

/** questa classe ha il compito di gestire le istanze della classe giocatore e permettere l'input all'utente **/
/*<<entity>>**/

public class Giocatore {
    public String input() {
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();
        return userInput;
    }
    public Giocatore(){
    }
}