package pt.ist.rest.service;

import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.domain.Cliente;

public class EsvaziarTabuleiroService extends PortalRestauranteService{

	ClienteDto cliente;
	
	public EsvaziarTabuleiroService(ClienteDto c){
		cliente = c;
	}
	
	@Override
	public void dispatch() throws NotFoundException {
		
		Cliente c = getCliente(cliente.getUsername());
		if(c == null)
			throw new NotFoundException("O username " + cliente.getUsername() +  " não corresponde a um cliente.");
		else c.esvaziaTabuleiro();
	}
}
