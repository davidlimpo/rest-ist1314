package pt.ist.rest.exception;

public class WrongPassException extends RestException{

	private static final long serialVersionUID = 1L;
	
	public WrongPassException(String message) {
		super.message = message;
	}
	
	public WrongPassException(){}
}
