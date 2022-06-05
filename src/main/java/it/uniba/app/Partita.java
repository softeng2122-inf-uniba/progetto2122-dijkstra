package it.uniba.app;

/** classe che descrive la sessione di una partita.
 * Boundary class
*/
public final class Partita {
    /**
     *  Matrice contenete i caratteri dei tentativi effettuati.
    */
    private final String[][] matriceTentativi;    
    /**
     * variabile booleana per uscire dalla partita.
     */
    private boolean quit;    
    /**
     * contatore del numero di tentativi che sono stati effettuati
    */    
    private int numeroTentativiEffettuati;  


    /**
     *  Metod costruttore.
     * @param numbOfWords
     * @param numbOfTries
    */
    Partita(final int numbOfWords, final int numbOfTries) {
        System.out.println("Creazione partita...");
        matriceTentativi = new String[numbOfTries][numbOfWords];
        quit = false;
        //inizializzazione matriceTentativi
        for (int i = 0; i < numbOfTries; i++) {
            for (int j = 0; j < numbOfWords; j++) {
                matriceTentativi[i][j] = "_";
            }
        }

        stampaMatrice(0);
        System.out.println("Partita creata! Puoi cominciare a giocare");
    }

    /** classe che descrive la sessione di una partita 
     * @param  startingRow
     * Boundary class 
    */
    private void stampaMatrice(final int startingRow) {

        for (int i = startingRow; i < App.NUMEROTENTATIVIMASSIMI; i++) {
            for (int j = 0; j < App.NUMEROLETTEREMASSIME; j++) {
                System.out.print("\t" + matriceTentativi[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * funzione principale per inserire un tentativo o un comando
     */
    public void playGame() {

        boolean youWin = false;             //variabile per controllo se si ha vinto la partita
        while (!quit && !youWin && numeroTentativiEffettuati < App.NUMEROTENTATIVIMASSIMI) {
            boolean wasCommand = false;     //variabile di controllo per differenziare i tentativi effettuati da eventuali comandi inseriti
                 
            System.out.println("Inserire tentativo n " + (numeroTentativiEffettuati + 1) + ": ");
            String inputUser = App.getGiocatore().input();
            
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
                        App.esci();
                        break;
                    case ABBANDONA:
                        wasCommand = true;
                        quitGame();
                        break;
                    default:
                        break;
                }
                
                
            } catch(InputUserNotValid e) {
                System.out.println(e.getMessage());
                wasCommand = true;
            }
            if (!wasCommand) {
                if (Analizzatore.analizzatoreSintattico(inputUser)) {
                    
                    String[] token = inputUser.split("");
                    if (inputUser.length()< App.NUMEROLETTEREMASSIME ){
                    	System.out.println("Tentativo incompleto");
                   }else if (inputUser.length() > App.NUMEROLETTEREMASSIME){
                    	System.out.println("Tentativo eccessivo");
                   }else {
                    	System.arraycopy(token, 0, matriceTentativi[numeroTentativiEffettuati], 0, App.NUMEROLETTEREMASSIME);   //copia l'input dell'utente all'interno della matrice        
                    	numeroTentativiEffettuati++;            
                    	youWin = stampaColoriTentativi();
                    }
                }else if (inputUser.length() == 0){
                	System.out.println("Tentativo incompleto");
                }else {
                    System.out.println("Tentativo non valido!");
                }
            }
        }
        if(youWin) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Parola segreta indovinata\nNumero tentativi: " + numeroTentativiEffettuati);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++\n");
        }else if(numeroTentativiEffettuati == App.NUMEROTENTATIVIMASSIMI) {
            System.out.println("Hai raggiunto il numero massimo di tentativi\nLa parola segreta e': " +  App.getParola());
        }
        System.out.println("Abbandono partita in corso...");
    }

    /**
     * funzione di calcolo dei colori corrispondenti ai tentativi effettuati.
     * @return youWin
    */
    private boolean stampaColoriTentativi() {
        //checkWin :  variabile di supporto per controllare se ci sia almeno una parola con tutti i caratteri verdi
        //necessaria quando si fa /new <parola> per controllare se la nuova parola segreta è già stata inserita 
        boolean checkWin;
        boolean youWin = false;

        int i;
        for(i = 0; i < numeroTentativiEffettuati; i++) {
            checkWin = true; 
            
            String token = "";
            
            for(int k = 0; k < App.NUMEROLETTEREMASSIME; k++) {
                token = token.concat(matriceTentativi[i][k]);
            }
            
            Analizzatore.Colore[] coloriCaratteri = Analizzatore.analizzatoreTentativo(token, App.getParola());

            for (int j = 0; j < App.NUMEROLETTEREMASSIME; j++) {
                if(null != coloriCaratteri[j]){
                    switch (coloriCaratteri[j]) {
                        case VERDE:
                            System.out.print("\t\u001B[42m" + matriceTentativi[i][j] + "\u001B[0m");
                            break;
                        case GIALLO:
                            System.out.print("\t\u001B[43m" + matriceTentativi[i][j] + "\u001B[0m");
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
            }
            System.out.println();
            if(checkWin) {
                youWin = true;
            } 
        }
        stampaMatrice(i);
        return youWin;
    }
    
    /**
     * metodo che consente l'abbandono della partita.
     */
    private void quitGame(){
        System.out.println("_______________________________");
        System.out.println("Si desidera abbandonare la partita?\nDigitare y o s per confermare");

        String risposta = App.getGiocatore().input();
        risposta = risposta.toLowerCase(); 
        
        if(risposta.equals("y")|| risposta.equals("s")){
             quit = true; 
        }else{
             System.out.println("_______________________________");
             System.out.println("Abbandono annullato");
        }        
      
    }
    
}