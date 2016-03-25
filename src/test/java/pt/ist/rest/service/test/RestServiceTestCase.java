package pt.ist.rest.service.test;

import java.util.Set;

import junit.framework.TestCase;
import jvstm.Atomic;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Utilizador;

public abstract class RestServiceTestCase extends TestCase {
	static {
		if(FenixFramework.getConfig() == null) {
			FenixFramework.initialize(new Config() {{
				domainModelPath="src/main/dml/domain.dml";
				dbAlias = "//localhost:3306/restdb"; 
		        dbUsername = "rest";
		        dbPassword = "r3st";
		        rootClass=PortalRestaurante.class;
				}});
		}
	}
	
	protected RestServiceTestCase(String msg){
		super(msg);
	}
	
	protected RestServiceTestCase() {
		super();
	}
	
	protected void setUp() {
		cleanRest();
	}
	
	protected void tearDown() {
		cleanRest();
	}
	
	@Atomic
	protected void addUtilizador(String nome, String username, String password, String morada, String email) {
		PortalRestaurante portal = FenixFramework.getRoot();
		portal.addUtilizador(new Cliente(nome, username, password, morada, email));
	}
	
	@Atomic
	protected void addPrato(String nome, Set<Alimento> alimentos){
		PortalRestaurante portal = FenixFramework.getRoot();
		portal.addPrato(new Prato(portal, nome, alimentos));
	}
	
	@Atomic
	protected void cleanRest() {
		PortalRestaurante p = FenixFramework.getRoot();
		
		Set<Utilizador> utilizadores  =  p.getUtilizadorSet();
		utilizadores.clear();
		
		Set<Restaurante> restaurantes  =  p.getRestauranteSet();
		restaurantes.clear();
		
		Set<Prato> pratos  =  p.getPratoSet();
		pratos.clear();
	}
}
