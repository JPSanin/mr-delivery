package exceptions;

public class OrderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public OrderNotFoundException( int code) {
		super("Cannot Find Order with code: "+code);
		
	}
}
