package pt.ist.chequerefeicao;

public class InvalidPayeeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPayeeException(String payee) {
	super("Invalid payee : " + payee);
    }
}
