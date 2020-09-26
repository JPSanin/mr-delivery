package exceptions;


public class ProductNotFoundException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	

	public ProductNotFoundException( int c) {
		super("Cannot Find Product with code: "+c);
		
	}
}
