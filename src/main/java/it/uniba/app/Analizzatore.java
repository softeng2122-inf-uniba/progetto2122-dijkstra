package it.uniba.app;

/**Classe con lo scopo di analizzare gli input inseriti dall'utente per l'identificazine
 dei: tentativi, tipo di comando inserito e, nel caso di una parola, sia stata inserita
 una parola valida*/

/**<<Control>>*/
public final class Analizzatore{
    
    public enum Colore{GRIGIO,GIALLO,VERDE};
    public enum Comando {NUOVA, MOSTRA, GIOCA, ESCI, ABBANDONA, AIUTO, SETPAROLA, GETPAROLA, ERRORE};
    
    private static String[] token;
    private static final String stringNuova = new String("/new");
    private static final String stringMostra = new String("/show");
    private static final String stringHelp = new String("/help");
    private static final String stringLessLessH = new String("--help");
    private static final String stringLessHelp = new String("-h");
    private static final String stringPlay = new String("/play");
    private static final String stringExit = new String("/exit");
    private static final String stringQuit = new String("/quit"); 
    
    public static Colore[] analizzatoreTentativo(String input,String parolaSegreta){
        
        Colore[] coloriCaratteri = new Colore[input.length()];
        
        for(int i=0;i<coloriCaratteri.length;i++){
            
            coloriCaratteri[i]=Colore.GRIGIO;
            
        }
        
        for(int i=0;i<input.length();i++){

            if(input.charAt(i) == parolaSegreta.charAt(i)){

                coloriCaratteri[i]=Colore.VERDE;

            }

        }
        
        for(int i=0;i<input.length();i++){

            if(coloriCaratteri[i]!=Colore.VERDE){

                for(int j=0;j<parolaSegreta.length();j++){

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

    public static Comando analizzatoreComando(String inputUser) throws InputUserNotValid {

        if(inputUser.length() > 0) {

            token = inputUser.trim().split(" ");

            if (token.length == 1) {

                if (token[0].equalsIgnoreCase(stringMostra)) {
                    return Comando.MOSTRA;
                } else if (token[0].equalsIgnoreCase(stringHelp) || token[0].equalsIgnoreCase(stringLessLessH)
                        || token[0].equalsIgnoreCase(stringLessHelp)) {
                    return Comando.AIUTO;
                } else if (token[0].equalsIgnoreCase(stringPlay)) {
                	return Comando.GIOCA;
                } else if (token[0].equalsIgnoreCase(stringExit)) {
                    return Comando.ESCI;
                } else if (token[0].equalsIgnoreCase(stringQuit)) {
                    return Comando.ABBANDONA;
                } else if (token[0].charAt(0) == '/') {
                    throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                }

            }
            
            else if(token.length == 2){
                
                if (token[0].equalsIgnoreCase(stringNuova)) {
                    return Comando.NUOVA;
                }
                
                else throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
                
            }
            
            else throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");

        } 
        
        return Comando.ERRORE;
	}
    
    public static boolean analizzatoreSintattico(String inputUser) {
        token = inputUser.trim().toLowerCase().split(" ");
        return inputUser.matches("[a-z]+") && token.length == 1;
    }
}