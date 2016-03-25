package pt.ist.rest.service;

import javax.xml.registry.JAXRException;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;

public abstract class RegistaPagamentoService extends PortalRestauranteService {
	
	protected ClienteDto cliente;
	
	
	@Override
	public void dispatch() throws NotFoundException {

		Cliente c = getCliente(cliente.getUsername());
		Compra compra = c.getCompraPendente();
		if(c != null){
			if(compra != null){
				c.efectuarPagamento(compra);
				registaFatura(compra);
			}
			else throw new NotFoundException("Não existem compras pendentes.");
		}
		else throw new NotFoundException("O username " + cliente.getUsername() + " nao existe.");
	}
	
	public abstract void registaFatura(Compra compra);

}
