package pt.ist.rest.exception;

public class NegativeBalanceException extends RestException{
	
	private static final long serialVersionUID = 1L;

	public NegativeBalanceException(String message) {
		super.message = message;
	}
	
	public NegativeBalanceException(){}
}
