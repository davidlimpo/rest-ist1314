package pt.ist.chequerefeicao;

public class InvalidCheckException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCheckException(String checkNumber) {
	super("Invalid check number: " + checkNumber);
    }
}
