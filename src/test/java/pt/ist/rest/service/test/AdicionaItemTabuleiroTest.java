package pt.ist.rest.service.test;

import java.util.HashSet;
import java.util.Set;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.AdicionarItemTabuleiroService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;


public class AdicionaItemTabuleiroTest extends RestServiceTestCase{

	private static String CLIENTE_NOME = "Lima";
	private static String CLIENTE_USERNAME = "Rodrigo";
	private static String CLIENTE_PASSWORD = "patricio123";
	private static String CLIENTE_MORADA = "Estadio da Luz";
	private static String CLIENTE_EMAIL = "lima11@slbenfica.pt";
	private static String PRATO_NOME = "Strogonoff";
	private static String PRATO_NOME2 = "Bolonhesa";
	private static String RESTAURANTE_NOME = "O Silva";
	private static String RESTAURANTE_MORADA = "123 Rua Falsa";
	
	@Override
	protected void setUp(){
		super.setUp();
		addUtilizador(CLIENTE_NOME, CLIENTE_USERNAME, CLIENTE_PASSWORD, CLIENTE_MORADA, CLIENTE_EMAIL);
	}
	
	@Atomic
	public void testAdicionaItemTabuleiroUmItem(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		ClienteDto cDto = new ClienteDto(CLIENTE_USERNAME);
		Set<Alimento> alimentos = new HashSet<Alimento>();
		Prato p1 = new Prato(portal, PRATO_NOME, alimentos);
		Prato p2 = new Prato(portal, PRATO_NOME2, alimentos);
		Restaurante r = new Restaurante(RESTAURANTE_NOME, RESTAURANTE_MORADA);
		r.addPrato(p1);
		r.addPrato(p2);
		portal.addRestaurante(r);
		RestauranteDto rDto = new RestauranteDto(RESTAURANTE_NOME);
		PratoDto pDto = new PratoDto(PRATO_NOME, rDto);
		QuantidadeDto qDto = new QuantidadeDto(1);
		
		AdicionarItemTabuleiroService service = new AdicionarItemTabuleiroService(cDto, pDto, qDto);
		
		//Act
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST AdicionarItemTabuleiroUmItemService: " + e.getMessage());
		}
		
		//Assert
		assertEquals("O cliente Rodrigo deve ter um prato", 1, portal.getClienteEspecifico(CLIENTE_USERNAME).getCompraPendente().existePrato(p1).getQuantidade());
		assertNull("O cliente Rodrigo nao deve ter o prato Bolonhesa", portal.getClienteEspecifico(CLIENTE_USERNAME).getCompraPendente().existePrato(p2));
	}
	
	@Atomic
	public void testAdicionaItemTabuleiroDoisItems(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Cliente c = portal.getClienteEspecifico(CLIENTE_USERNAME);
		ClienteDto cDto = new ClienteDto(CLIENTE_USERNAME);
		
		Set<Alimento> alimentos = new HashSet<Alimento>();
		Prato p1 = new Prato(portal, PRATO_NOME, alimentos);
		Prato p2 = new Prato(portal, PRATO_NOME2, alimentos);
		
		Quantidade q = new Quantidade(1);
		q.setPrato(p1);
		
		Compra compra = new Compra();
		compra.addQuantidade(q);
		c.addCompra(compra);
		c.efectuarPagamento(compra);
		
		Restaurante r = new Restaurante(RESTAURANTE_NOME, RESTAURANTE_MORADA);
		r.addPrato(p1);
		r.addPrato(p2);
		portal.addRestaurante(r);
		RestauranteDto rDto = new RestauranteDto(RESTAURANTE_NOME);
		PratoDto pDto = new PratoDto(PRATO_NOME2, rDto);
		QuantidadeDto qDto = new QuantidadeDto(1);
		
		AdicionarItemTabuleiroService service = new AdicionarItemTabuleiroService(cDto, pDto, qDto);
		
		//Act
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST AdicionarItemTabuleiroDoisItemsService: " + e.getMessage());
		}
		
		//Assert
		assertEquals("O cliente Rodrigo deve ter um parto", 1, portal.getClienteEspecifico(CLIENTE_USERNAME).getCompraPendente().existePrato(p2).getQuantidade());
		assertNull("O cliente Rodrigo nao deve ter o prato Strogonoff", portal.getClienteEspecifico(CLIENTE_USERNAME).getCompraPendente().existePrato(p1));
	}
}
