package pt.ist.rest.exception;

public class AlreadyExistsException extends RestException{

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String message) {
		super.message = message;
	}
	
	public AlreadyExistsException(){}
}