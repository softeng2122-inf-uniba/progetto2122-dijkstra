package it.uniba.app;

class InputUserNotValid extends Exception {
	InputUserNotValid() {}
	
	InputUserNotValid(String msg) {
		super(msg);
	}
}