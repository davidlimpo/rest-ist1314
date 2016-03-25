package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.TabuleiroDto;

public class ObterTabuleiroDeComprasService extends PortalRestauranteService{
	
	private ClienteDto cliente;
	private List<PratoDto> listaPratos = new ArrayList<PratoDto>();
	private int custoTotalTabuleiro;
	private TabuleiroDto tabuleiroDto;
	
	public ObterTabuleiroDeComprasService(ClienteDto cDto){
		cliente = cDto;
	}
	
	@Override
	public void dispatch() throws NotFoundException {
		
		Cliente c = getCliente(cliente.getUsername());
		
		
		Compra compra = c.getCompraPendente();
		
		if(compra == null){
			throw new NotFoundException("O cliente " + c.getUserName() + " nao tem compras pendentes");
		} else {
			
			for(Quantidade q : compra.getQuantidade()) {
				QuantidadeDto qDto = new QuantidadeDto(q.getQuantidade());
				PratoDto pDto = new PratoDto(q.getPrato().getNome(), q.getPrato().getPreco(), q.getPrato().getCalorias(), q.getPrato().getId(), qDto);
				listaPratos.add(pDto);
				custoTotalTabuleiro += q.getPrato().getPreco();
			}			
			
			this.tabuleiroDto = new TabuleiroDto(listaPratos, custoTotalTabuleiro, c.getSaldo());
		}
	}
	
	public TabuleiroDto getResult(){
		return tabuleiroDto;
	}
	
}
