package pt.ist.rest.exception;

public class LimitedExceededException extends RestException{

	private static final long serialVersionUID = 1L;
	
	public LimitedExceededException(String message) {
		super.message = message;
	}
	
	public LimitedExceededException(){}
}
