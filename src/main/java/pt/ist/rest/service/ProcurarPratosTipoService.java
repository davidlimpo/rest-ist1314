package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteDto;

public class ProcurarPratosTipoService extends PortalRestauranteService{
 
	private AlimentoDto alimento;
	private List<PratoDto> listaPratos = new ArrayList<PratoDto>();


	public ProcurarPratosTipoService(AlimentoDto a){
		this.alimento = a;
	}
	
	@Override
	public void dispatch() {
		PortalRestaurante portal = FenixFramework.getRoot();

		
		for(Restaurante r : portal.getRestaurante()) 
			for(Prato p : r.getPrato())
				if(alimento.getTipo().equals("vegetariano")){
					if(p.vegetariano())
						listaPratos.add(new PratoDto(p.getNome(), p.getPreco(), p.getCalorias(), p.getId(), p.getClassificacao(), new RestauranteDto(r.getNome())));
			
				}else if(alimento.getTipo().equals("carne")){
					if(p.tipoCarne())
						listaPratos.add(new PratoDto(p.getNome(), p.getPreco(), p.getCalorias(), p.getId(), p.getClassificacao(), new RestauranteDto(r.getNome())));
				
				}else {
					if(p.tipoPeixe())
						listaPratos.add(new PratoDto(p.getNome(), p.getPreco(), p.getCalorias(), p.getId(), p.getClassificacao(), new RestauranteDto(r.getNome())));
				}	
	}
	
	public List<PratoDto> getResult(){
		return listaPratos;
	}
}
