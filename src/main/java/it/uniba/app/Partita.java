package it.uniba.app;

public final class Partita {

    private String[][] matriceTentativi;
    private boolean quit = false;
    private int numeroTentativiEffetuati;
    
    Partita(int numbOfWords, int numbOfTries) {
        System.out.println("Creazione partita...");
        
        matriceTentativi = new String [numbOfTries][numbOfWords];
        quit = false;
        
        numeroTentativiEffetuati = 0;
        
        /**inizializzazione matriceTentativi*/
        for(int i=0; i< numbOfTries; i++){
            for (int j=0; j<numbOfWords; j++){
                matriceTentativi[i][j] = "_";
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
            System.out.println("Inserire tentativo n " + numeroTentativiEffettuati + ": ");
            String inputUser = App.giocatore.input();
            
            try {
                Analizzatore.Comando comando = Analizzatore.analizzatoreComando(inputUser);
                switch(comando){
                    case NUOVA : App.setParola(inputUser);
                        if()
                        break;
                    case MOSTRA :
                        
                        if(App.getParola() != null) {
                            System.out.println("La parola segreta inserita e': " + App.getParola());
                        } else System.out.println("Parola segreta non impostata");
                        
                        break;
                    case AIUTO : App.getHelp();
                        break;
                    case GIOCA : System.out.println("Sei gia' in partita!");
                        break;
                    case ESCI : App.exit();
                        break;
                    case ABBANDONA : quitGame();
                        break;
                    default: System.out.println("Errore nell'inserimento del comando");
                        break;
                }
            } catch (InputUserNotValid e) {
                System.out.println(e.getMessage());
            }
            
            if(Analizzatore.analizzatoreSintattico(inputUser)) {
                
                
                
            } else {
                System.out.println("Tentativo non valido!");
            }
            
        }
        
    }

}