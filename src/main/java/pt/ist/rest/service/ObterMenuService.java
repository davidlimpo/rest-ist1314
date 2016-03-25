package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class ObterMenuService extends PortalRestauranteService {

	private RestauranteDto restaurante;
	private List<PratoDto> listaPratos = new ArrayList<PratoDto>();

	
	public ObterMenuService(RestauranteDto r){
		restaurante = r;
	}
		
	@Override
	public void dispatch() {
		
		Restaurante r = getRestaurante(restaurante.getNome());
		
		RestauranteDto rDto = new RestauranteDto(r.getNome(), r.getNome(), r.getClassificacao());
		
		for(Prato p : r.getPrato()) {
			PratoDto pDto = new PratoDto(p.getNome(), p.getPreco(), p.getCalorias(), p.getId(), p.getClassificacao(), rDto);
			listaPratos.add(pDto);
		}	
	}
	
	public List<PratoDto> getResult(){
		return listaPratos;
	}
}
