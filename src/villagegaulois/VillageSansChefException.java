package villagegaulois;

public class VillageSansChefException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VillageSansChefException() {
	}
	
	
	public VillageSansChefException(String message) {
		super(message);
	}
	
	public VillageSansChefException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VillageSansChefException(Throwable cause) {
		super(cause);
	}

}
