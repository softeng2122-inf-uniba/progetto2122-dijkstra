package it.uniba.app;

public final class Analizzatore{
    
    public enum Colore {GRIGIO,GIALLO,VERDE};
    
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

			if (token[0].equalsIgnoreCase("/nuova")) {
                return 1;
			} else if (token[0].equalsIgnoreCase("/mostra")) {
                return 2;
			} else if (token[0].equalsIgnoreCase("/help") || token[0].equalsIgnoreCase("--h")
					|| token[0].equalsIgnoreCase("-help")) {
                return 3;
			} else if (token[0].equalsIgnoreCase("/play")) {
                return 4;
			} else if (token[0].equalsIgnoreCase("/exit")) {
                return 5;
			} else if (token[0].equalsIgnoreCase("/quit")) {
                return 6;
			} else if (token[0].charAt(0) == '/') {
				throw new InputUserNotValid("Comando non valido, digita /help per avere maggiori informazioni");
			}

		} else
			throw new InputUserNotValid("Comando non valido");

	}
     
}