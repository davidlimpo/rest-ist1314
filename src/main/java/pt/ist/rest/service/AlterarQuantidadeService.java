package pt.ist.rest.service;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;

public class AlterarQuantidadeService extends PortalRestauranteService {

	private ClienteDto cliente;
	private PratoDto prato;
	private QuantidadeDto novaQuantidade;
	
	public AlterarQuantidadeService(ClienteDto c, PratoDto p, QuantidadeDto novaQuantidade) {
		cliente = c;
		prato = p;
		this.novaQuantidade = novaQuantidade;
	}
	
	
	@Override
	public void dispatch() throws NotFoundException {

		Cliente c = getCliente(cliente.getUsername());
		
		if(c == null)
			throw new NotFoundException("O username " + cliente.getUsername() + " não corresponde a um cliente.");	
	
		Compra compra = c.getCompraPendente();
		
		for(Quantidade q : compra.getQuantidade())
			if(q.getPrato().getNome().equals(prato.getNome()))
					q.setQuantidade(novaQuantidade.getQuantidade());
		
	}

}
