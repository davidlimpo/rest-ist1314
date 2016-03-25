package pt.ist.rest.exception;

public class NoMoneyException extends RestException{
	
	private static final long serialVersionUID = 1L;

	public NoMoneyException(String message) {
		super.message = message;
	}

	public NoMoneyException(){}
	
}
