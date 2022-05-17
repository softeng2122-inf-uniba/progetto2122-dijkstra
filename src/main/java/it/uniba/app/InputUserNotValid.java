class InputUserNotValid extends Exception {
	InputUserNotValid() {}
	
	InputUserNotValid(String msg) {
		super(msg);
	}
}