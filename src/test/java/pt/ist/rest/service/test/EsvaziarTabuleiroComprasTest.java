package pt.ist.rest.service.test;

import java.util.HashSet;
import java.util.Set;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Carne;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.EsvaziarTabuleiroService;
import pt.ist.rest.service.dto.ClienteDto;

public class EsvaziarTabuleiroComprasTest extends RestServiceTestCase{
	
	private static int QUANTIDADE = 3;
	private static String CLIENTE_NOME = "Cardozo";
	private static String CLIENTE_USERNAME = "Tacuara";
	private static String CLIENTE_PASSWORD = "patricio";
	private static String CLIENTE_MORADA = "Estadio da Luz";
	private static String CLIENTE_EMAIL = "tacuara7@slbenfica.pt";
	private static String ALIMENTO = "Frango";
	private static String PRATO_NOME = "Frango a Patricio";
	
	@Override
	protected void setUp(){
		super.setUp();
		addUtilizador(CLIENTE_NOME, CLIENTE_USERNAME, CLIENTE_PASSWORD, CLIENTE_MORADA, CLIENTE_EMAIL);
	}
	
	@Atomic
	public void testEsvaziarTabuleiroUmaCompra(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Compra compra = new Compra();
		Quantidade quantidade = new Quantidade(QUANTIDADE);
		
		Set<Alimento> alimentos = new HashSet<Alimento>();
		Carne frango = new Carne(ALIMENTO);
		alimentos.add(frango);
		
		Prato p = new Prato(portal, PRATO_NOME, alimentos);
		
		quantidade.setPrato(p);
		compra.addQuantidade(quantidade);
		Cliente c = portal.getClienteEspecifico(CLIENTE_USERNAME);
		c.addCompra(compra);
	
		ClienteDto cdto = new ClienteDto(CLIENTE_USERNAME);
		EsvaziarTabuleiroService service = new EsvaziarTabuleiroService(cdto);
		
		//Act
		
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST EsvaziaTabuleiroUmaCompraService: " + e.getMessage());
		}
		//Assert
		
		assertNull("O tabuleiro de compras deve estar vazio", c.getCompraPendente());
	}
	
	@Atomic
	public void testEsvaziarTabuleiroVariasCompras(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Compra compra1 = new Compra();
		Compra compra2 = new Compra();
		Quantidade quantidade = new Quantidade(QUANTIDADE);
		
		Set<Alimento> alimentos = new HashSet<Alimento>();
		Carne frango = new Carne(ALIMENTO);
		alimentos.add(frango);
		
		Prato p = new Prato(portal, PRATO_NOME, alimentos);
		
		quantidade.setPrato(p);
		compra1.addQuantidade(quantidade);
		compra2.addQuantidade(quantidade);
		Cliente c = portal.getClienteEspecifico(CLIENTE_USERNAME);
		c.addCompra(compra1);
		c.efectuarPagamento(compra1);
		c.addCompra(compra2);
	
		ClienteDto cdto = new ClienteDto(CLIENTE_USERNAME);
		EsvaziarTabuleiroService service = new EsvaziarTabuleiroService(cdto);
		
		//Act
		
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST EsvaziaTabuleiroVariasComprasService: " + e.getMessage());
		}
		//Assert
		
		assertNull("O tabuleiro de compras deve estar vazio", c.getCompraPendente());
		assertEquals("Estao la as duas compras?", 1, c.getCompraCount());
	}
	
	@Atomic
	public void testEsvaziarTabuleiroSemCompras(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		
		Cliente c = portal.getClienteEspecifico(CLIENTE_USERNAME);
	
		ClienteDto cdto = new ClienteDto(CLIENTE_USERNAME);
		EsvaziarTabuleiroService service = new EsvaziarTabuleiroService(cdto);
		
		//Act
		
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST EsvaziaTabuleiroSemComprasService: " + e.getMessage());
		}
		//Assert
		assertTrue("O tabuleiro deve estar vazio", c.getCompra().isEmpty());
		assertEquals("O tabuleiro deve estar vazio", 0, c.getCompraCount());
	}
}
