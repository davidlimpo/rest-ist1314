package pt.ist.chequerefeicao;

import java.util.List;

import pt.ist.rest.domain.Cliente;

public abstract class ChequeRefeicao {
	
	public abstract void pagarComCheque(List<String> checks, Cliente c, ChequeRefeicao cheque) throws InvalidCheckException, CheckAlreadyUsedException;
	public abstract int cashChecks(String payee, List<String> checks) throws InvalidCheckException, CheckAlreadyUsedException;

}
