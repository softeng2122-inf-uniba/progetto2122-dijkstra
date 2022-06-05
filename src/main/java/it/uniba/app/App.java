
package it.uniba.app;

/**
 * classe main dell'applicazione
 * 
 * Boundary class
 */
public final class App {          
    /**
     * Riferimento alla parola segreta impostata.
    */
    private static String parolaSegreta = null;
    /**
     * Riferimento al numero massimo di lettere ammesse.
    */
    static final int NUMEROLETTEREMASSIME  = 5;
    /**
     * Riferimento al numero massimo di tentativi .
    */
    static final int NUMEROTENTATIVIMASSIMI = 6;

    /**
     * Oggetto di classe giocatore.
    */
    private static Giocatore giocatore= new Giocatore();
    
    
    /**
     * Restituisce il giocatore.
     * @return giocatore
    */
    public static Giocatore getGiocatore(){
        return giocatore;
    }
    
    /**
     * Restituisce il messaggio di benvenuto durante l'avvio del gioco.
     * @return messaggio di benvenuto.
    */
    public String getGreeting(){
        return "BENVENUTO IN WORDLE";
    }
    
    /**
     * Imposta la nuova parola segreta.
     * @param x nuova parola da impostare.
     * @exception InputUserNotValid richiamato se utente non valido.
    */
    public static void setParola(String x) throws InputUserNotValid{
        if (x.length() < NUMEROLETTEREMASSIME){
            throw new InputUserNotValid("Parola segreta troppo corta");
        }else if (x.length() > NUMEROLETTEREMASSIME){
            throw new InputUserNotValid("Parola segreta troppo lunga");
        }else if (!x.matches("[a-z]*")){
            throw new InputUserNotValid("Parola segreta non valida");
        }else{
            System.out.println("OK");
            parolaSegreta = x; 
        }  
    }
    
    /**
     * Restituisce la parola segreta.
     * @return messaggio di benvenuto.
    */
    public static String getParola(){
        return parolaSegreta;
    }
    
    /**
     * Iniazia una nuova partita.
    */
    public static void gioca(){
        if (parolaSegreta != null){
            Partita partita= new Partita(NUMEROLETTEREMASSIME,NUMEROTENTATIVIMASSIMI);
            partita.playGame();    
        }else{
            System.out.println("Parola segreta mancante");
        }
    }
    
    /**
     * Stampa a video gli aiuti.
    */
    public static void getHelp(){
        System.out.println("Ciao giocatore!");
        System.out.println("Digita il comando '/gioca' per avviare una nuova partita");
        System.out.println("Digita il comando '/abbandona' per uscire dalla partita. "
                            + "Attenzione questo comando e' disponibile solo se hai avviato la partita!");
        System.out.println("Digita il comando '/nuova <parolaSegreta>' per inserire una nuova parola segreta. "
                            + "Attenzione! La parola segreta da inserire deve avere lunghezza "
                            + "pari a " + NUMEROLETTEREMASSIME + " caratteri");
        System.out.println("Digita il comando '/mostra' per mostrare la parola segreta");
        System.out.println("Digita il comando '/esci' per uscire dall'applicazione\n\n");
        System.out.println("##### RULES OF THE GAME #####");
        System.out.println("Ad ogni tentativo le lettere possono colorarsi in tre modi:");
        System.out.println(" - grigie se la lettera in questione non compare nella parola da indovinare");
        System.out.println(" - gialle se vi compare ma non in quella posizione");
        System.out.println(" - verdi se hai indovinato la posizione precisa");
        System.out.println("\nRicorda che hai a disposizione " + NUMEROTENTATIVIMASSIMI + " tentativi per indovinare la parola."); 
    }
    
    /**
     * Chiude il programma, implemnetata anche una richiesta di conferma.
    */
    public static void esci(){
        System.out.println("_______________________________");
        System.out.println("Si desidera terminare il programma ?\nDigitare y o s per confermare");
        String risposta = getGiocatore().input();
        risposta = risposta.toLowerCase(); 
        if (risposta.equals("y")|| risposta.equals("s")){
             System.out.println("Chiusura in corso....");
             System.exit(0); 
        }else{
             System.out.println("_______________________________");
             System.out.println("Chiusura annullata");
        }        
    }
    
    /**
     * Main principale del programma.
     * @param args
    */
    public static void main(final String[] args) {  
        
        System.out.println(new App().getGreeting());
        
        if (args.length > 0){
            try{
                if(Analizzatore.analizzatoreComando(args[0]) == Analizzatore.Comando.AIUTO){
                    getHelp();
                }
            }catch(InputUserNotValid e){
                System.err.println(e.getMessage());
            }  
        } else {
            System.out.println("*** Per avere la lista dei comandi digita '-h' / '--help' ***");
        }
        while(true) {
            System.out.println("Introdurre un comando: ");
            String inputUser = getGiocatore().input();
            
            try {
                Analizzatore.Comando comando = Analizzatore.analizzatoreComando(inputUser);
                switch(comando){
                    case NUOVA : 
                        String[] parola = inputUser.split(" ");
                        setParola(parola[1]);
                        break;
                    case MOSTRA :
                        if(getParola() != null) {
                            System.out.println("La parola segreta inserita e': " + getParola());
                        } else{ 
                            System.out.println("Parola segreta mancante");
                        }
                        break;
                    case AIUTO : getHelp();
                        break;
                    case GIOCA : gioca();
                        break;
                    case ESCI : esci();
                        break;
                    default: System.out.println("Comando non valido. Digita /help per avere maggiori informazioni");
                        break;
                }
            } catch (InputUserNotValid e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

