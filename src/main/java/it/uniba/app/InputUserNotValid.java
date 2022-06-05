package it.uniba.app;

/**
 * Classe per la gestione dei casi in cui giocatore/paroliere inseriscano input invalidi.
 * NoECB class.
 */
class InputUserNotValid extends Exception {
	InputUserNotValid() {
        }
	
        /**
         * metodo costruttore per il richiamo di una eccezione.
         * @param msg 
         */
	InputUserNotValid(String msg) {
		super(msg);
	}
}