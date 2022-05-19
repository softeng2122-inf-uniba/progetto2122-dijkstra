package it.uniba.app;

/** classe che descrive la sessione di una partita */

/** Boundary class */

public final class Partita {
    private String[][] matriceTentativi;    // matrice contenete i caratteri dei tentativi effettuati
    private boolean quit = false;           // variabile booleana per uscire dalla partita
    private int numeroTentativiEffettuati;  // contatore del numero di tentativi che sono stati effettuati


    // metodo costruttore
    Partita(int numbOfWords, int numbOfTries) {
        System.out.println("Creazione partita...");

        matriceTentativi = new String[numbOfTries][numbOfWords];
        quit = false;

        /**
         * inizializzazione matriceTentativi
         */
        for (int i = 0; i < numbOfTries; i++) {
            for (int j = 0; j < numbOfWords; j++) {
                matriceTentativi[i][j] = "_";
            }
        }

        stampaMatrice();
        System.out.println("Partita creata! Puoi cominciare a giocare");
    }

    //metodo di stampa della matrice
    private void stampaMatrice() {

        for (int i = 0; i < App.numeroTentativiMassimi; i++) {
            for (int j = 0; j < App.numeroLettereMassime; j++) {
                System.out.print("\t" + matriceTentativi[i][j]);
            }
            System.out.println();
        }
    }

    //funzione principale per inserire un tentativo o un comando
    public void playGame() {

        boolean youWin = false;             //variabile per controllo se si ha vinto la partita
        while (quit == false && youWin == false && numeroTentativiEffettuati < App.numeroTentativiMassimi) {
            boolean wasCommand = false;     //variabile di controllo per differenziare i tentativi effettuati da eventuali comandi inseriti
                 
            System.out.println("Inserire tentativo n " + (numeroTentativiEffettuati + 1) + ": ");
            String inputUser = App.giocatore.input();

            try {                           //controllo di inserimento di un eventuale comando
                Analizzatore.Comando comando;
                comando = Analizzatore.analizzatoreComando(inputUser);
                switch (comando) {
                    case NUOVA:
                        App.setParola(inputUser);
                        youWin = stampaColoriTentativi();
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
                if (Analizzatore.analizzatoreSintattico(inputUser)) {
                    
                    String[] token = inputUser.split("");
                    System.arraycopy(token, 0, matriceTentativi[numeroTentativiEffettuati], 0, App.numeroLettereMassime);   //copia l'input dell'utente all'interno della matrice        
                    numeroTentativiEffettuati++;
                                        
                    youWin = stampaColoriTentativi();

                } else {
                    System.out.println("Tentativo non valido!");
                }
            }
        }
        
        if(youWin == true) {
            System.out.println("Hai vinto!");
        }
        else if(numeroTentativiEffettuati == App.numeroTentativiMassimi) {
            System.out.println("E' stato raggiunto il numero dei tentativi possibili");
        }
        
        System.out.println("Chiusura partita in corso...");
    }

    //funzione di calcolo dei colori corrispondenti ai tentativi effettuati
    /*
    checkWin :  variabile di supporto per controllare se ci sia almeno una parola con tutti i caratteri verdi
                necessaria quando si fa /new <parola> per controllare se la nuova parola segreta è già stata inserita    
    */
    private boolean stampaColoriTentativi() {
        boolean checkWin, youWin = false;
        
        for(int i = 0; i < numeroTentativiEffettuati; i++) {
            checkWin = true; 
            
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
                        checkWin = false;
                        break;
                    case GRIGIO:
                        System.out.print("\t" + matriceTentativi[i][j]);
                        checkWin = false;
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
            
            if(checkWin == true) {
                youWin = true;
            } 
        }
        
        return youWin;
    }
    
    private void quitGame(){
        
        System.out.println("_______________________________");
        System.out.println("Si desidera abbandonare la partita?\nDigitare y o s per confermare");

        String risposta = App.giocatore.input();
        risposta = risposta.toLowerCase(); 
        
        if(risposta.equals("y")|| risposta.equals("s")){
             System.out.println("Abbandono partita in corso....");
             quit = true; 
        }else{
             System.out.println("_______________________________");
             System.out.println("Abbandono annullato");
        }        
      
    }
    
}