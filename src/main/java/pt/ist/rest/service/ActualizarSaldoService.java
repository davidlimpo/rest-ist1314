package pt.ist.rest.service;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;

public class ActualizarSaldoService extends PortalRestauranteService {

	private String username;
	private float saldo;
	
	public ActualizarSaldoService(String username, float valor){
		this.username = username;
		this.saldo = valor;
	}
	
	@Override
	public final void dispatch() throws RestException {
		Cliente c = getCliente(username);
		if(c == null)
			throw new NotFoundException("O username " + username + " não é um cliente");
		else c.somaSaldo(saldo);
	}
}
