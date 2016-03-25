package pt.ist.rest.exception;

public class ClientLikeException extends RestException{

	private static final long serialVersionUID = 1L;

	public ClientLikeException(String message) {
		super.message = message;
	}
	
	public ClientLikeException(){}
}
