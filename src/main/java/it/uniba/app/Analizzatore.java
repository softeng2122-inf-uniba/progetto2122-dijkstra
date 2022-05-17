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

    public static void analizzatoreComando(String inputUser) throws InputUserNotValid {

        token = inputUser.trim().split(" ");

		if (token[0].equalsIgnoreCase("/nuova")) {

        } else if (token[0].equalsIgnoreCase("/mostra")) {

        } else if (token[0].equalsIgnoreCase("/help") || token[0].equalsIgnoreCase("--h")
        || token[0].equalsIgnoreCase("-help")) {

        }

	}
     
}