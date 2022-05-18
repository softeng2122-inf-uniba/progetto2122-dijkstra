package it.uniba.app;

public final class Partita {

    private char[][] matriceTentativi;
    
    Partita(int numbOfWords, int numbOfTries) {
        System.out.println("Creazione partita...");
        
        matriceTentativi = new char [numbOfTries][numbOfWords];
        quit = false;
        
        /**inizializzazione matriceTentativi*/
        for(int i=0; i< numbOfTries; i++){
            for (int j=0; j<numbOfWords; j++){
                matriceTentativi[i][j]='_';
            }
        }
        
        stampaMatrice(); 
        System.out.println("Partita creata! Puoi cominciare a giocare");
    }
    
    private void stampaMatrice() {
        
        for (int i=0; i < App.numeroLettereMassime; i++){
            for(int j=0; j< App.numeroLettereMassime; j++){
                System.out.print("\t"+ matriceTentativi[i][j]);
            }
            System.out.println();
        } 
        
    }

    public void playGame() {

        while(!quit) {
            System.out.println("Inserire tentativo n°" + numeroTentativiEffettuati + ": ");
            String inputUser = App.giocatore.input();
            
            if(Analizzatore.analizzatoreSintattico(inputUser)) {
                
                
                
            } else {
                System.out.println("Tentativo non valido!");
            }
            
        }
        
    }

}