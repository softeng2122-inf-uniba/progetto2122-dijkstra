package it.uniba.app;

import java.util.Scanner;

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
    
    public static void esci(){
        
       Scanner scanner = new Scanner(System.in);
        
        System.out.println("_______________________________");
        System.out.println("Si desidera terminare il programma ?\nDigitare y o s per confermare");

        char risposta = scanner.next().charAt(0);
         switch(risposta) {    
            case 'y':
            case 'Y':   
            case 's':
            case 'S':
                System.out.println("Chiusura in corso....");
                System.exit(0);   
                break;  
            default:    
                System.out.println("_______________________________");
                System.out.println("Chiusura annullata");
                break; 
         }
        
      
    }
    

   
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
            System.out.println("la parola segreta è "+ parola);
            break;
            case Analizzatore.Comando.SETPAROLA: setParola(parolaSegreta);
            break;
            default: System.out.println("Errore nell'inserimento del comando");
            break;
        }*/
        

    }
}
