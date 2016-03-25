package pt.ist.rest.exception;

public class NotFoundException extends RestException{

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super.message = message;
	}
	
	public NotFoundException(){}
}
