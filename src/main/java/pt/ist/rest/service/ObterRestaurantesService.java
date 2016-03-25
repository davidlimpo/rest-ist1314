package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.RestauranteDto;

public class ObterRestaurantesService extends PortalRestauranteService{
	
	private List<RestauranteDto> listaRestaurantes = new ArrayList<RestauranteDto>();
		
	@Override
	public void dispatch() {
		PortalRestaurante pr = FenixFramework.getRoot();
		
		for(Restaurante r : pr.getRestaurante()) {
			RestauranteDto rDto = new RestauranteDto(r.getNome(), r.getMorada(), r.getClassificacao());
			listaRestaurantes.add(rDto);
		}	
	}
	
	public List<RestauranteDto> getResult(){
		return listaRestaurantes;
	}
}
