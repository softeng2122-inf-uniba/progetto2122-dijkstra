package it.uniba.app;

public final class Partita {

    private char[][] matriceTentativi;
    
    Partita(int numbOfWords, int numbOfTries) {
        System.out.println("Creazione partita...");
        
        matriceTentativi = new char [numbOfTries][numbOfWords];
        
        /**inizializzazione matriceTentativi*/
        for(int i=0; i < numbOfTries; i++){
            for (int j=0; j < numbOfWords; j++){
                matriceTentativi[i][j]='_';
            }
        }
         
        System.out.println("Partita creata! Puoi cominciare a giocare");
    }

}