package exceptions;


public class OrderNotFoundException extends Exception {


	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instantiates a new order not found exception.
	 *
	 * @param code the code
	 */
	public OrderNotFoundException( int code) {
		super("Cannot Find Order with code: "+code);
		
	}
}
