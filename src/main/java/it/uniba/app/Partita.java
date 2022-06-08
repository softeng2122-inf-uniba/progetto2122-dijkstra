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
     *  Variabile costante contenente il numero massimo di lettere di cui
     *  pu� essere composta la parola
     */
    private final int numeroletteremassime;
    /**
     * Variabile costante contenente il numero massimo di tentativi che
     * si possono effettuare
     */
    private final int numerotentativimassimi;
    /**
     * variabile booleana per uscire dalla partita.
     */
    private boolean quit;    
    /**
     * contatore del numero di tentativi che sono stati effettuati
    */    
    private int numeroTentativiEffettuati;  


    /**
     * Metodo costruttore.
     * @param numbOfWords
     * @param numbOfTries
    */
    Partita(final int numbOfWords, final int numbOfTries) {
        System.out.println("Creazione partita...");
        numeroletteremassime = numbOfWords;
        numerotentativimassimi = numbOfTries;
        matriceTentativi = new String[numerotentativimassimi][numeroletteremassime];
        quit = false;
        //inizializzazione matriceTentativi
        for (int i = 0; i < numerotentativimassimi; i++) {
            for (int j = 0; j < numeroletteremassime; j++) {
                matriceTentativi[i][j] = "_";
            }
        }

        stampaColoriTentativi();
        System.out.println("Partita creata! Puoi cominciare a giocare");
    }

    /**
     * funzione principale per inserire un tentativo o un comando
     */
    public void playGame() {

        boolean youWin = false;             //variabile per controllo se si ha vinto la partita
        while (!quit && !youWin && numeroTentativiEffettuati < numerotentativimassimi) {
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
                    if (inputUser.length() < numeroletteremassime ){
                    	System.out.println("Tentativo incompleto");
                   }else if (inputUser.length() > numeroletteremassime){
                    	System.out.println("Tentativo eccessivo");
                   }else {
                    	System.arraycopy(token, 0, matriceTentativi[numeroTentativiEffettuati], 0, numeroletteremassime);   //copia l'input dell'utente all'interno della matrice        
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
        }else if(numeroTentativiEffettuati == numerotentativimassimi) {
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
        //necessaria quando si fa /nuova <parola> per controllare se la nuova parola segreta è già stata inserita 
        boolean checkWin;
        boolean youWin = false;

        int i;
        for(i = 0; i < numeroTentativiEffettuati; i++) {
            checkWin = true; 
            
            String token = "";
            
            for(int k = 0; k < numeroletteremassime; k++) {
                token = token.concat(matriceTentativi[i][k]);
            }
            
            Analizzatore.Colore[] coloriCaratteri = Analizzatore.analizzatoreTentativo(token, App.getParola());

            for (int j = 0; j < numeroletteremassime; j++) {
                if(null != coloriCaratteri[j]) {
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

        for (int k = i; k < numerotentativimassimi; k++) {
            for (int j = 0; j < numeroletteremassime; j++) {
                System.out.print("\t" + matriceTentativi[k][j]);
            }
            System.out.println();
        }

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
        
        if(risposta.equals("y")|| risposta.equals("s")) {
             quit = true; 
        }else {
             System.out.println("_______________________________");
             System.out.println("Abbandono annullato");
        }        
      
    }
    
}