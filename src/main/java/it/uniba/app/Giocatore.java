package it.uniba.app;

import java.util.Scanner;

/** questa classe ha il compito di gestire le istanze della classe 
* giocatore e permettere l'input all'utente. 
* Entity class.
*/
public class Giocatore {
    
    /**
     * Costruttore.
    */
    public Giocatore(){
    }
    
    /**
     * Gestisce input giocatore da terminale.
     * @return input da terminale
    */
    public static String input() {
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();
        return userInput;
    }  
}
