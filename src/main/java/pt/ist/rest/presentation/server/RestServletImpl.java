package pt.ist.rest.presentation.server;

import java.util.List;

import pt.ist.rest.DatabaseBootstrap;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.exception.WrongPassException;
import pt.ist.rest.presentation.client.RestServlet;
import pt.ist.rest.service.AdicionarItemTabuleiroService;
import pt.ist.rest.service.AlterarQuantidadeService;
import pt.ist.rest.service.ObterMenuService;
import pt.ist.rest.service.ObterRestaurantesService;
import pt.ist.rest.service.ObterTabuleiroDeComprasService;
import pt.ist.rest.service.ProcurarPratosNomeService;
import pt.ist.rest.service.ProcurarPratosTipoService;
import pt.ist.rest.service.RegistaPagamentoLocalService;
import pt.ist.rest.service.RegistaPagamentoRemotoService;
import pt.ist.rest.service.VerificarPasswordService;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.TabuleiroDto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */

public class RestServletImpl extends RemoteServiceServlet implements
		RestServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void login(ClienteDto cliente) throws NotFoundException, WrongPassException {
		VerificarPasswordService service = new VerificarPasswordService(cliente);
		service.execute();
	}

	@Override
	public void initServer(String serverType) {
		DatabaseBootstrap.init();
	}

	@Override
	public List<RestauranteDto> obterRestaurantes() {
		ObterRestaurantesService service = new ObterRestaurantesService();
		service.execute();
		
		return service.getResult();
	}

	@Override
	public List<PratoDto> mostrarMenu(RestauranteDto restaurante) {
		ObterMenuService service = new ObterMenuService(restaurante);
		service.execute();
		
		return service.getResult();
	}

	@Override
	public void adicionarPrato(ClienteDto cliente, PratoDto prato,
			QuantidadeDto quantidade) throws NotFoundException {
		AdicionarItemTabuleiroService service = new AdicionarItemTabuleiroService(cliente, prato, quantidade);
		service.execute();
	}

	@Override
	public TabuleiroDto mostrarTabuleiro(ClienteDto cliente) throws NotFoundException {
		ObterTabuleiroDeComprasService service = new ObterTabuleiroDeComprasService(cliente);
		service.execute();
		
		return service.getResult();
	}

	@Override
	public void alterarQuantidade(ClienteDto cliente, PratoDto prato,
			QuantidadeDto quantidade) throws NotFoundException {
		AlterarQuantidadeService service = new AlterarQuantidadeService(cliente, prato, quantidade);
		service.execute();		
	}

	@Override
	public void pagarLocalTabuleiro(ClienteDto cliente) throws NotFoundException {
		RegistaPagamentoLocalService service = new RegistaPagamentoLocalService(cliente);
		service.execute();
	}
	
	@Override
	public void pagarRemotoTabuleiro(ClienteDto cliente) throws NotFoundException {
		RegistaPagamentoRemotoService service = new RegistaPagamentoRemotoService(cliente);
		service.execute();
	}
	
	@Override
	public List<PratoDto> procurarPratosNome(String prato) {
		ProcurarPratosNomeService service = new ProcurarPratosNomeService(prato);
		service.execute();
		
		return service.getResult();
	}
	
	@Override
	public List<PratoDto> procurarPratosTipo(AlimentoDto aDto) {
			ProcurarPratosTipoService service = new ProcurarPratosTipoService(aDto);
			service.execute();
			
			return service.getResult();
	}
}
