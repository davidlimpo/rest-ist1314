package pt.ist.rest.service;

import javax.xml.registry.JAXRException;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.service.dto.ClienteDto;

public class RegistaPagamentoRemotoService extends RegistaPagamentoService {

	public RegistaPagamentoRemotoService(ClienteDto c){
		super.cliente = c;
	}
	
	@Override
	public void registaFatura(Compra compra) {
		PortalRestaurante portal = FenixFramework.getRoot();
		portal.registarFaturaRemoto(compra);	
	}
}
