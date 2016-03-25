package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;

public class RegistaPagamentoLocalService extends RegistaPagamentoService {

	public RegistaPagamentoLocalService(ClienteDto c){
		super.cliente = c;
	}

	@Override
	public void registaFatura(Compra compra) {
		PortalRestaurante portal = FenixFramework.getRoot();
		portal.registarFaturaLocal(compra);	
	}
}
