package pt.ist.chequerefeicao;

public class CheckAlreadyUsedException extends Exception {

	private static final long serialVersionUID = 1L;

	public CheckAlreadyUsedException(String checkNumber) {
	super("Check already registered: " + checkNumber);
    }
}
