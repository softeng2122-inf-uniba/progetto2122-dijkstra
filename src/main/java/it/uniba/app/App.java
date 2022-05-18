package it.uniba.app;

/**classe main dell'applicazione*/

/**Boundary class*/

public final class App {          
    private static String parolaSegreta= new String();
    private static int numeroLettereMassime = 5;
    //Giocatore giocatore= new Giocatore();
    
    public String getGreeting(){
        return "BENVENUTO IN WORDLE";
    }
    
    public static void setParola(String x) throws InputUserNotValid{
    	if (x.length() < numeroLettereMassime)
			throw new InputUserNotValid("Parola segreta troppo corta");
		else if (x.length() > numeroLettereMassime)
			throw new InputUserNotValid("Parola segreta troppo lunga");
		else if (!x.matches("[a-z]*"))
			throw new InputUserNotValid("Parola segreta non valida");
		else {
			System.out.println("OK");
			parolaSegreta = x; 
		}  
    }
    
    public static String getParola(){
        return parolaSegreta;
    }
    
    /*public static void gioca(){
        
    }*/
    
    /*public static String getHelp(){
        
    }*/
    
   /* public static void esci(){
        
    }*/
    

   
    public static void main(final String[] args) {  
       System.out.println(new App().getGreeting());
        /*if(args[0].equals("--help")|| args[0].equals("-h")){
            String messaggioHelp= getHelp();
            System.out.println(messaggioHelp);
        }
        String comandoGiocatore= giocatore.input();
        Analizzatore.Comando comando= Analizzatore.analizzatoreComando(comandoGiocatore);
        switch(comando){
            case Analizzatore.Comando.PLAY: gioca();
            break;
            case Analizzatore.Comando.HELP: String messaggioHelp= getHelp();
            break;
            case Analizzatore.Comando.EXIT: esci();
            break;
            case Analizzatore.Comando.GETPAROLA: String parola= getParola();
            break;
            case Analizzatore.Comando.SETPAROLA: setParola(parolaSegreta);
            break;
            default: System.out.println("Errore nell'inserimento del comando");
            break;
        }*/
    }
}
