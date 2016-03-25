package pt.ist.rest.exception;

public class NoPayBoardFoundException extends RestException {
	
	private static final long serialVersionUID = 1L;

	public NoPayBoardFoundException(String message) {
		super.message = message;
	}

	public NoPayBoardFoundException(){}
	
}
