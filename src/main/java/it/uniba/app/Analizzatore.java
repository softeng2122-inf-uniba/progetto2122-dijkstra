package it.uniba.app;
/**
 * Classe con lo scopo di analizzare gli input inseriti dall'utente per 
 * l'identificazine dei: tentativi, tipo di comando inserito e, nel 
 * caso di una parola, sia stata inserita una parola valida.
 * Control class.
 */
public final class Analizzatore {
    
    /**
     * Token in input contenente il comando.
    */
    private static String[] token;
    /**
     * Stringa che identifica il comando nuova.
    */
    private static final String STRINGNUOVA = "/nuova";
    
    /**
     * Stringa che identifica il comando mostra.
    */
    private static final String STRINGMOSTRA = "/mostra";
    /**
     * Stringa che identifica il comando help.
    */
    private static final String STRINGHELP = "/help";
    /**
     * Stringa che identifica il comando gioca.
    */
    private static final String STRINGPLAY = "/gioca";
    /**
     * Stringa che identifica il comando esci.
    */
    private static final String STRINGEXIT = "/esci";
    
    /**
     * Stringa che identifica il comando esci.
    */
    private static final String STRINGQUIT = "/abbandona";
    
    /**
     * Costruttore classe Analizzatore.
     */
     public Analizzatore() {
        
    }
    
    /**
     * metodo che analizza i tentativi fatti dall'utente dal suo input assegnando il colore al carattere.
     * @param input
     * @param parolaSegreta
     * @return Colore[]
     */
    public static Colori[] analizzatoreTentativo(final String input,final String parolaSegreta) {
        Colori[] coloriCaratteri = new Colori[input.length()];
        for (int i = 0; i < coloriCaratteri.length; i++) {
            coloriCaratteri[i] = Colori.GRIGIO;
        }
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == parolaSegreta.charAt(i)) {
                coloriCaratteri[i] = Colori.VERDE;
            }
        }
        for (int i = 0; i < input.length(); i++) {
            if(coloriCaratteri[i] != Colori.VERDE) {
                for (int j = 0; j < parolaSegreta.length(); j++) {
                    if(coloriCaratteri[j] != Colori.VERDE) {
                            if(input.charAt(i) == parolaSegreta.charAt(j)) {
                                coloriCaratteri[i] = Colori.GIALLO;
                             }
                    }
                }
            }
        }
        return coloriCaratteri;
    }

    /**
     * metodo che analizza il comando tramite l'input dell'utente.
     * @param inputUser
     * @return Comando
     * @throws InputUserNotValid 
     */
    public static Comandi analizzatoreComando(String inputUser) throws InputUserNotValid {
        if(inputUser.length() > 0) {
            token = inputUser.trim().split(" ");
            if (token.length == 1) {
                
                if (token[0].equalsIgnoreCase(STRINGMOSTRA)) {
                    return Comandi.MOSTRA;
                } else if (token[0].equalsIgnoreCase(STRINGHELP)) {
                    return Comandi.AIUTO;
                } else if (token[0].equalsIgnoreCase(STRINGPLAY)) {
                    return Comandi.GIOCA;
                } else if (token[0].equalsIgnoreCase(STRINGEXIT)) {
                    return Comandi.ESCI;
                } else if (token[0].equalsIgnoreCase(STRINGQUIT)) {
                    return Comandi.ABBANDONA;
                } else if (token[0].charAt(0) == '/') {
                    throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                }
            }else if(token.length == 2) {
                if (token[0].equalsIgnoreCase(STRINGNUOVA)) {
                    return Comandi.NUOVA;
                }
                else {
                    throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                }
            }
            else {
                throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
            }
        } 
        return Comandi.ERRORE;
    }
 
    /**
     * metodo che verifica che una stringa sia priva di spazi e composta da soli caratteri alfabetici.
     * @param inputUser
     * @return boolean
     */
    public static boolean analizzatoreSintattico( final String inputUser) {
        token = inputUser.trim().toLowerCase().split(" ");
        return inputUser.matches("[a-z]+") && token.length == 1;
    }
}