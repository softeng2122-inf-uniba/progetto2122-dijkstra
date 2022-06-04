package it.uniba.app;

/**
 * Classe con lo scopo di analizzare gli input inseriti dall'utente per 
 * l'identificazine dei: tentativi, tipo di comando inserito e, nel 
 * caso di una parola, sia stata inserita una parola valida
 */

/**
 * <<control>>
 */

public final class Analizzatore{
    
    /**
     * lista di colori usati sui caratteri durante la partita
     */
    public enum Colore{GRIGIO, GIALLO, VERDE};
    
    /**
     * lista dei comandi accettati
     */
    public enum Comando {NUOVA, MOSTRA, GIOCA, ESCI, ABBANDONA, AIUTO, SETPAROLA, GETPAROLA, ERRORE};
    
    private static String[] token;
    private static final String STRINGNUOVA = "/nuova";
    private static final String STRINGMOSTRA = "/mostra";
    private static final String STRINGHELP = "/help";
    private static final String STRINGLESSLESSH = "--help";
    private static final String STRINGLESSHELP = "-h";
    private static final String STRINGPLAY = "/gioca";
    private static final String STRINGEXIT = "/esci";
    private static final String STRINGQUIT = "/abbandona";
    
    /**
     * Costruttore classe Analizzatore.
     */
    public Analizzatore(){
        
    }
    
    /**
     * metodo che analizza i tentativi fatti dall'utente dal suo input assegnando il colore al carattere
     * @param input
     * @param parolaSegreta
     * @return Colore[]
     */
    public static Colore[] analizzatoreTentativo(String input,String parolaSegreta){
        Colore[] coloriCaratteri = new Colore[input.length()];
        
        for (int i=0;i<coloriCaratteri.length;i++){
            coloriCaratteri[i]=Colore.GRIGIO;
        }
       
        for (int i=0;i<input.length();i++){
            if(input.charAt(i) == parolaSegreta.charAt(i)){
                coloriCaratteri[i]=Colore.VERDE;
            }
        }
        
        for (int i=0;i<input.length();i++){
            if(coloriCaratteri[i]!=Colore.VERDE){
                for (int j=0;j<parolaSegreta.length();j++){
                    if(coloriCaratteri[j]!=Colore.VERDE){
                            if(input.charAt(i)==parolaSegreta.charAt(j)){
                                coloriCaratteri[i] = Colore.GIALLO;
                             }
                    }
                }
            }
        }
        
        return coloriCaratteri;
    }

    /**
     * metodo che analizza il comando tramite l'input dell'utente
     * @param inputUser
     * @return Comando
     * @throws InputUserNotValid 
     */
    public static Comando analizzatoreComando(String inputUser) throws InputUserNotValid {

        if(inputUser.length() > 0) {
            token = inputUser.trim().split(" ");
            if (token.length == 1) {
                
                if (token[0].equalsIgnoreCase(STRINGMOSTRA)) {
                    return Comando.MOSTRA;
                } else if (token[0].equalsIgnoreCase(STRINGHELP) || token[0].equalsIgnoreCase(STRINGLESSLESSH)
                        || token[0].equalsIgnoreCase(STRINGLESSHELP)) {
                    return Comando.AIUTO;
                } else if (token[0].equalsIgnoreCase(STRINGPLAY)) {
                    return Comando.GIOCA;
                } else if (token[0].equalsIgnoreCase(STRINGEXIT)) {
                    return Comando.ESCI;
                } else if (token[0].equalsIgnoreCase(STRINGQUIT)) {
                    return Comando.ABBANDONA;
                } else if (token[0].charAt(0) == '/') {
                    throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                }
            }
            
            else if(token.length == 2){
                
                if (token[0].equalsIgnoreCase(STRINGNUOVA)) {
                    return Comando.NUOVA;
                }
                else {
                    throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                }
            }
            else{
                throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
            }
        } 
        return Comando.ERRORE;
    }
    
    /**
     * metodo che verifica che una stringa sia priva di spazi e composta da soli caratteri alfabetici
     * @param inputUser
     * @return boolean
     */
    public static boolean analizzatoreSintattico(String inputUser) {
        token = inputUser.trim().toLowerCase().split(" ");
        return inputUser.matches("[a-z]+") && token.length == 1;
    }
}