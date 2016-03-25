package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class AdicionarItemTabuleiroService extends PortalRestauranteService {

	private ClienteDto cliente;
	private PratoDto prato;
	private QuantidadeDto quantidade;
	
	public AdicionarItemTabuleiroService(ClienteDto c, PratoDto p, QuantidadeDto q) {
		cliente = c;
		prato = p;
		quantidade = q;
	}
	
	@Override
	public void dispatch() throws NotFoundException {
		
		RestauranteDto rdto = prato.getRestauranteDto();
		PortalRestaurante pr = FenixFramework.getRoot();
		Restaurante r = pr.getRestauranteEspecifico(rdto.getNome());
		Prato p = r.getPratoEspecifico(prato.getNome());
		Cliente c = getCliente(cliente.getUsername());
		
		if(c == null)
			throw new NotFoundException("O username " + cliente.getUsername() + " não corresponde a um cliente.");	
		
		Quantidade q = new Quantidade(quantidade.getQuantidade());
		q.setPrato(p);
			

		Compra compra = c.getCompraPendente();
		
		if(compra == null) {
			compra = new Compra();
			compra.addQuantidade(q);
			c.addCompra(compra);
		}
		else{
			compra.addQuantidade(q);
		}
	}
}
