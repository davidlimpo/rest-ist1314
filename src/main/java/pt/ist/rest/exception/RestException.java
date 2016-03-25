package pt.ist.rest.exception;

public abstract class RestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	protected String message;
	
	public String getMessage() {
		return this.message;
	}
	
	public RestException(){}
}
