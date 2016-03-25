package pt.ist.rest.exception;

public class WrongManagerException extends RestException{

	private static final long serialVersionUID = 1L;
	
	public WrongManagerException(String message) {
		super.message = message;
	}
	
	public WrongManagerException(){}
}
