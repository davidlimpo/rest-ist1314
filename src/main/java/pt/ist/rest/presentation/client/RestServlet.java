package pt.ist.rest.presentation.client;

import java.util.List;

import javax.xml.registry.JAXRException;

import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.exception.WrongPassException;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.TabuleiroDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface RestServlet extends RemoteService {
	
	public void login(ClienteDto cliente) throws NotFoundException, WrongPassException;
	public List<RestauranteDto> obterRestaurantes();
	public List<PratoDto> mostrarMenu(RestauranteDto restaurante);
	public void adicionarPrato(ClienteDto cliente, PratoDto prato, QuantidadeDto quantidade) throws NotFoundException;
	public TabuleiroDto mostrarTabuleiro(ClienteDto cliente) throws NotFoundException;
	public void alterarQuantidade(ClienteDto cliente, PratoDto prato, QuantidadeDto quantidade) throws NotFoundException;
	public void pagarRemotoTabuleiro(ClienteDto cliente) throws NotFoundException;
	public void pagarLocalTabuleiro(ClienteDto cliente) throws NotFoundException;
	public void initServer(String serverType);
	public List<PratoDto> procurarPratosNome(String nome);
	public List<PratoDto> procurarPratosTipo(AlimentoDto aDto);
}
