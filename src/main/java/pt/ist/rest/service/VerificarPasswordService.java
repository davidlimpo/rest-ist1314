package pt.ist.rest.service;

import pt.ist.rest.domain.Utilizador;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.WrongPassException;
import pt.ist.rest.service.dto.ClienteDto;

public class VerificarPasswordService extends PortalRestauranteService{

	private ClienteDto cliente;
	
	public VerificarPasswordService(ClienteDto c){
		this.cliente = c;
	}
	
	public void dispatch() throws NotFoundException, WrongPassException {
		
		Utilizador u = getCliente(cliente.getUsername());
		
		if(u == null)
				throw new NotFoundException("O username não pertence a um cliente");
		else
			if(u.verificaPassword(cliente.getPassword()))
				System.out.println("Password do Cliente " + u.getUserName() + " correcta.");
			else
				throw new WrongPassException("Password do Cliente " + u.getUserName() + " incorrecta.");
	}
}
