package exceptions;


public class RestaurantNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RestaurantNotFoundException( int ti) {
		super("Cannot Find Restaurant with taxID: "+ti);
		
	}


	
}

