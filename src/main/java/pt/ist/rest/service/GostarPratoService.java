package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class GostarPratoService extends PortalRestauranteService{

	private ClienteDto cliente;
	private PratoDto prato;
	
	public GostarPratoService(ClienteDto c, PratoDto p){
		this.cliente = c;
		this.prato = p;
	}
	
	public void dispatch() throws NotFoundException {
		PortalRestaurante portal = FenixFramework.getRoot();

		RestauranteDto r = prato.getRestauranteDto();
		Restaurante r1 = portal.getRestauranteEspecifico(r.getNome());

		Prato p = r1.getPratoEspecifico(prato.getNome());
		Cliente c = getCliente(cliente.getUsername());
		if(c != null)
			c.likePrato(p);
		else throw new NotFoundException("O username não pertence a um cliente");
	}
}
