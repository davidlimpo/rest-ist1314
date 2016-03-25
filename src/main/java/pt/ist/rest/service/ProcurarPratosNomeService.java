package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.exception.RestException;

import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class ProcurarPratosNomeService extends PortalRestauranteService{

	private List<PratoDto> listaPratos = new ArrayList<PratoDto>();
	private String nomePrato;
	
	public ProcurarPratosNomeService(String nome){
		this.nomePrato = nome;
	}
	
	@Override
	public void dispatch() {
		
		PortalRestaurante pr = FenixFramework.getRoot();
		
		for(Restaurante r : pr.getRestaurante()) 
			for(Prato p : r.getPrato())	
				
				if(p.getNome().contains(nomePrato)){
					PratoDto pDto = new PratoDto(p.getNome(), p.getPreco(), p.getCalorias(), p.getId(), p.getClassificacao(), new RestauranteDto(r.getNome(), r.getMorada(), r.getClassificacao()));
					listaPratos.add(pDto);
				}
	}
	
	public List<PratoDto> getResult(){
		return listaPratos;
	}
}