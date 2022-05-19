package it.uniba.app;

public final class Partita {

    private String[][] matriceTentativi;
    private boolean quit = false;
    private int numeroTentativiEffettuati;
    
    Partita(int numbOfWords, int numbOfTries) {
        System.out.println("Creazione partita...");
        
        matriceTentativi = new String [numbOfTries][numbOfWords];
        quit = false;
        
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
            boolean wasCommand = false;

            System.out.println("Inserire tentativo n " + numeroTentativiEffettuati + ": ");
            String inputUser = App.giocatore.input();
            
            try {
                Analizzatore.Comando comando;
                comando = Analizzatore.analizzatoreComando(inputUser);
                switch (comando) {
                    case NUOVA:
                        App.setParola(inputUser);
                        wasCommand = true;
                        break;
                    case MOSTRA:
                        wasCommand = true;
                        if (App.getParola() != null) {
                            System.out.println("La parola segreta inserita e': " + App.getParola());
                        } else {
                            System.out.println("Parola segreta non impostata");
                        }

                        break;
                    case AIUTO:
                        wasCommand = true;
                        App.getHelp();
                        break;
                    case GIOCA:
                        wasCommand = true;
                        System.out.println("Sei gia' in partita!");
                        break;
                    case ESCI:
                        wasCommand = true;
                        App.exit();
                        break;
                    case ABBANDONA:
                        wasCommand = true;
                        quitGame();
                        break;
                }
                
                
            } catch (InputUserNotValid e) {
                System.out.println(e.getMessage());
                wasCommand = true;
            }

            if(wasCommand == false) {
                if(Analizzatore.analizzatoreSintattico(inputUser)) {  

                    String[] token = inputUser.split("");
                    System.arraycopy(token, 0, matriceTentativi[numeroTentativiEffettuati], 0, App.numeroLettereMassime);           
                    numeroTentativiEffettuati++;
                
                } else {
                    System.out.println("Tentativo non valido!");
                }
            }
            
        }
        
    }

    private void stampaColorata() {
        
        for(int i = 0; i < numeroTentativiEffettuati; i++) {
            
            String token = "";
            
            for(int k = 0; k < App.numeroLettereMassime; k++) {
                token = token.concat(matriceTentativi[i][k]);
            }
            
            Analizzatore.Colore[] coloriCaratteri = Analizzatore.analizzatoreTentativo(token, App.getParola());
             
            for (int j = 0; j < App.numeroLettereMassime; j++) {
                
                if(null != coloriCaratteri[j]) switch (coloriCaratteri[j]) {
                    case VERDE:
                        System.out.print("\t\u001B[32m" + matriceTentativi[i][j] + "\u001B[0m");
                        break;
                    case GIALLO:
                        System.out.print("\t\u001B[33m" + matriceTentativi[i][j] + "\u001B[0m");
                        break;
                    case GRIGIO:
                        System.out.print("\t" + matriceTentativi[i][j]);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();

        }
        
    }

}