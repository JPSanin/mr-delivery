package exceptions;

public class ClientNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ClientNotFoundException( int docNum) {
		super("Cannot Find Client with document number: "+docNum);
		
	}
}
