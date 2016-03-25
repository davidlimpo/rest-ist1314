package pt.ist.rest.presentation.client;

import java.util.List;

import javax.xml.registry.JAXRException;

import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.WrongPassException;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.TabuleiroDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RestServletAsync {
		
	void login(ClienteDto cliente, AsyncCallback<Void> callback) throws NotFoundException, WrongPassException;
	void obterRestaurantes(AsyncCallback<List<RestauranteDto>> callback);
	void mostrarMenu(RestauranteDto restaurante, AsyncCallback<List<PratoDto>> callback);
	void adicionarPrato(ClienteDto cliente, PratoDto prato, QuantidadeDto quantidade, AsyncCallback<Void> callback) throws NotFoundException;
	void mostrarTabuleiro(ClienteDto cliente, AsyncCallback<TabuleiroDto> callback) throws NotFoundException;
	void alterarQuantidade(ClienteDto cliente, PratoDto prato, QuantidadeDto quantidade, AsyncCallback<Void> callback) throws NotFoundException;
	void pagarRemotoTabuleiro(ClienteDto cliente, AsyncCallback<Void> callback) throws NotFoundException;
	void pagarLocalTabuleiro(ClienteDto cliente, AsyncCallback<Void> callback) throws NotFoundException;
	void initServer(String serverType, AsyncCallback<Void> callback);
	void procurarPratosNome(String nome, AsyncCallback<List<PratoDto>> callback);
	void procurarPratosTipo(AlimentoDto aDto, AsyncCallback<List<PratoDto>> callback);

}
