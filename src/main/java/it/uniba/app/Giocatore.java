package it.uniba.app;

public class Giocatore {
    public String input() {
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.nextLine();
        return userInput;
    }
    public Giocatore(){
    }
}