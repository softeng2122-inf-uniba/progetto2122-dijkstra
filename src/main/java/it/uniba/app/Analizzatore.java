package it.uniba.app;

public final class Analizzatore{
    
    public enum Colore {GRIGIO,GIALLO,VERDE};

    private static String[] token;
    private static final String stringNuova = new String("/new");
	private static final String stringMostra = new String("/show");
	private static final String stringHelp = new String("/help");
	private static final String stringLessLessH = new String("--h");
	private static final String stringLessHelp = new String("-h");
	private static final String stringPlay = new String("/play");
	private static final String stringExit = new String("/exit");
	private static final String stringQuit = new String("/quit"); 
    
    public static Colore[] analizzatoreTentativo(String input,String parolaSegreta){
        
        Colore[] coloriCaratteri = new Colore[input.length()];
        
        for(int i=0;i<input.length();i++){
            
            coloriCaratteri[i]=Colore.GRIGIO;
            
        }
        
        for(int i=0;i<parolaSegreta.length();i++){
            
            for(int j=0;j<input.length();i++){
                
                if(input.charAt(j) == parolaSegreta.charAt(i) && i == j){
                    
                    coloriCaratteri[j]=Colore.VERDE;
                    
                }
                
                else if(input.charAt(j) == parolaSegreta.charAt(i) && i != j){
                    
                    coloriCaratteri[j]=Colore.GIALLO;
                    
                }
                
            }
            
        }
        
        return coloriCaratteri;
        
    }

    public static int analizzatoreComando(String inputUser) throws InputUserNotValid {

        token = inputUser.trim().split(" ");

		if (token.length <= 2) {

			if (token[0].equalsIgnoreCase(stringNuova)) {
                return 1;
			} else if (token[0].equalsIgnoreCase(stringMostra)) {
                return 2;
			} else if (token[0].equalsIgnoreCase(stringHelp) || token[0].equalsIgnoreCase(stringLessLessH)
					|| token[0].equalsIgnoreCase(stringLessHelp)) {
                return 3;
			} else if (token[0].equalsIgnoreCase(stringPlay)) {
                return 4;
			} else if (token[0].equalsIgnoreCase(stringExit)) {
                return 5;
			} else if (token[0].equalsIgnoreCase(stringQuit)) {
                return 6;
			} else if (token[0].charAt(0) == '/') {
				throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
			}

		} else
			throw new InputUserNotValid("Comando non valido");

	}
     
}