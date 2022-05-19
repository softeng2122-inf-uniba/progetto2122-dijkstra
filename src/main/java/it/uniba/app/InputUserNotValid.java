package it.uniba.app;

/**Classe per la gestione dei casi in cui giocatore/paroliere inseriscano input
 invalidi*/

/**<<NoECB>>*/
class InputUserNotValid extends Exception {
	InputUserNotValid() {}
	
	InputUserNotValid(String msg) {
		super(msg);
	}
}