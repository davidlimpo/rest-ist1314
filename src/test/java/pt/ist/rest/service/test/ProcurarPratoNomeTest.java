package pt.ist.rest.service.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.ProcurarPratosNomeService;
import pt.ist.rest.service.dto.PratoDto;

public class ProcurarPratoNomeTest extends RestServiceTestCase{
	

	private static String PRATO_NOME1 = "Frango a Patricio";
	private static String PRATO_NOME2 = "Pato a Patricio";
	private static String PESQUISA1 = "Pato";
	private static String PESQUISA2 = "Frango";
	private static String PESQUISA3 = "Patricio";
	private static String NOME_RESTAURANTE = "O Glorioso";
	private static String MORADA_RESTAURANTE = "Lisboa, Benfica";
	private Set<Alimento> alimentos = new HashSet<Alimento>();
	
	@Override
	protected void setUp(){
		super.setUp();
		addPrato(PRATO_NOME1, alimentos);
		addPrato(PRATO_NOME2, alimentos);
	}
	
	@Atomic
	public void testProcurarPratosNomeServicePratoUm(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Restaurante r = new Restaurante(NOME_RESTAURANTE, MORADA_RESTAURANTE);
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME1));
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME2));
		portal.addRestaurante(r);
		
		ProcurarPratosNomeService service = new ProcurarPratosNomeService(PESQUISA1);
		List<PratoDto> listResult;
		
		//Act
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST ProcurarPratosNomeService: " + e.getMessage());
		}
		
		//Assert
		listResult = service.getResult();
		assertEquals("A lista de pratos deve ter um prato", 1, listResult.size());
		assertEquals("A lista deve conter o prato Pato a Patricio", listResult.get(0).getNome(), PRATO_NOME2);
	}
	
	@Atomic
	public void testProcurarPratosNomeServicePratoDois(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Restaurante r = new Restaurante(NOME_RESTAURANTE, MORADA_RESTAURANTE);
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME1));
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME2));
		portal.addRestaurante(r);
		
		ProcurarPratosNomeService service = new ProcurarPratosNomeService(PESQUISA2);
		List<PratoDto> listResult;
		
		//Act
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST ProcurarPratosNomeService: " + e.getMessage());
		}
		
		//Assert
		listResult = service.getResult();
		assertEquals("A lista de pratos deve ter um prato", 1, listResult.size());
		assertEquals("A lista deve conter o prato Frango a Patricio", listResult.get(0).getNome(), PRATO_NOME1);
	}
	
	@Atomic
	public void testProcurarPratosNomeServicePratoTres(){
		//Arrange
		PortalRestaurante portal = FenixFramework.getRoot();
		Restaurante r = new Restaurante(NOME_RESTAURANTE, MORADA_RESTAURANTE);
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME1));
		r.addPrato(portal.getPratoEspecifico(PRATO_NOME2));
		portal.addRestaurante(r);
		
		ProcurarPratosNomeService service = new ProcurarPratosNomeService(PESQUISA3);
		List<PratoDto> listResult;
		
		//Act
		try{
			service.dispatch();
		} catch(NotFoundException e){
			fail("TEST ProcurarPratosNomeService: " + e.getMessage());
		}
		
		//Assert
		listResult = service.getResult();
		assertEquals("A lista de pratos deve ter dois pratos", 2, listResult.size());
		assertEquals("A lista deve conter o prato Frango a Patricio", listResult.get(0).getNome(), PRATO_NOME1);
		assertEquals("A lista deve conter o prato Pato a Patricio", listResult.get(1).getNome(), PRATO_NOME2);
	}
	
}
