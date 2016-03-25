package pt.ist.rest.presentationserver; 

import java.util.HashSet;
import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Carne;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Compra;
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Quantidade;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Utilizador;
import pt.ist.rest.domain.Vegetariano;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.AdicionarItemTabuleiroService;
import pt.ist.rest.service.GostarPratoService;
import pt.ist.rest.service.ObterRestaurantesService;
import pt.ist.rest.service.VerificarPasswordService;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;
	  
public class PresentationServer {
	
	//private ChequeRefeicao cheque;
	
    public static void main(String[] args) {
    	FenixFramework.initialize(new Config() {{
                dbAlias = "//localhost:3306/restdb"; 
                dbUsername = "rest";
                dbPassword = "r3st";
    		domainModelPath="src/main/dml/domain.dml";
    		rootClass=PortalRestaurante.class;
    	}});

    	System.out.println("Welcome to the PresentationServer application!");

    	boolean committed = false;
 //1  	
    	try {
    		committed = false;
    		Transaction.begin();
    		
    		PortalRestaurante rest = FenixFramework.getRoot();

        	Restaurante r1 = new Restaurante("Barriga Cheia", "Porto, Portugal");
        	Gestor g1 = (Gestor) rest.getUtilizadorEspecifico("pp");
        	r1.addGestor(g1);
        	
        	rest.addRestaurante(r1);	
    		
    	Transaction.commit();
    	committed = true;
    	
    	} catch(RestException re){
    			System.out.println("Erro: " + re.getMessage());
    	} finally {
    		if (!committed)
    			Transaction.abort();
    	}
 //2   	
    	try {
    		committed = false;
    		Transaction.begin();
    		
    		PortalRestaurante rest = FenixFramework.getRoot();
		
    		for (Utilizador u : rest.getUtilizadorSet()) {
    			if(u instanceof Gestor)
    				System.out.println("Gestor: " + u.getNome());
    			else
    				System.out.println("Cliente: " + u.getNome());
    		}
    		
    	Transaction.commit();
    	committed = true;
    	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	} finally {
    		if (!committed)
    			Transaction.abort();
    	}
 //3   	
    	try {

    		ObterRestaurantesService listaRestaurantes = new ObterRestaurantesService();
    		
    		listaRestaurantes.execute();
    		
            for (RestauranteDto r : listaRestaurantes.getResult())
                System.out.println("Restaurante: " + r.getNome() + " Morada: " + r.getMorada() + " Classificacao: " + r.getClassificacao());
        	
        	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());	
    	}
 //4
    	try {
    		committed = false;
    		Transaction.begin();

    		PortalRestaurante rest = FenixFramework.getRoot();
    		
        	Vegetariano v1 = new Vegetariano("batatas");
        	Carne c1 = new Carne("Bife");

        	Set<Alimento> alimentosPrato = new HashSet<Alimento>();
        	alimentosPrato.add(c1);
        	alimentosPrato.add(v1);
    		
    		Gestor g1 = (Gestor) rest.getUtilizadorEspecifico("pp");
        	Restaurante r1 = rest.getRestauranteEspecifico("Barriga Cheia");
        	Prato p1 = new Prato(rest, "Bitoque", alimentosPrato);
        	
        	r1.adicionaPrato(p1, g1);
        	
        	Transaction.commit();
        	committed = true;
        	        	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	} finally {
    		if (!committed)
    			Transaction.abort();
    	}
//5
    	try {

    		ObterRestaurantesService listaRestaurantes = new ObterRestaurantesService();
    		
    		listaRestaurantes.execute();
    		
            for (RestauranteDto r : listaRestaurantes.getResult())
                System.out.println("Restaurante: " + r.getNome() + " Classificacao: " + r.getClassificacao());
        	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());	
    	}
 //6	
    	try {

    		ClienteDto c = new ClienteDto("zeze");
    		RestauranteDto r = new RestauranteDto("Barriga Feliz");
    		
    		PratoDto p1 = new PratoDto("Bitoque", r);
    		PratoDto p2 = new PratoDto("Canja de galinha", r);
    		PratoDto p3 = new PratoDto("Bacalhau com batatas", r);
    		
    		GostarPratoService gosta1 = new GostarPratoService(c, p1);
    		GostarPratoService gosta2 = new GostarPratoService(c, p2);
    		GostarPratoService gosta3 = new GostarPratoService(c, p3);
    		
    		gosta1.execute();
    		gosta2.execute();
    		gosta3.execute();

    		
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	}
//7
    	try {

       		ClienteDto c = new ClienteDto("mariazinha");
    		RestauranteDto r = new RestauranteDto("Barriga Feliz");
    		PratoDto p1 = new PratoDto("Canja de galinha", r);
    		
    		GostarPratoService gosta = new GostarPratoService(c, p1);
    		gosta.execute();
		
        	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	}
    	
//8
    	try {
    		
    		ObterRestaurantesService listaRestaurantes = new ObterRestaurantesService();
    		
    		listaRestaurantes.execute();
    		
            for (RestauranteDto r : listaRestaurantes.getResult())
                System.out.println("Restaurante: " + r.getNome() + "Classificacao: " + r.getClassificacao());
            
    	} catch(RestException re){
    			System.out.println("Erro: " + re.getMessage());
    	}
 
  //9
    	try {
    		RestauranteDto r = new RestauranteDto("Barriga Feliz");
    		PratoDto p = new PratoDto("Canja de galinha", r);
    		QuantidadeDto q = new QuantidadeDto(3);
    		ClienteDto c = new ClienteDto("zeze");
    		
    		AdicionarItemTabuleiroService aits = new AdicionarItemTabuleiroService(c, p, q);
    		aits.execute();
    	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	}
    	
    //10
    	try {
    		RestauranteDto r = new RestauranteDto("Barriga Feliz");
    		PratoDto p = new PratoDto("Bacalhau com batatas", r);
    		QuantidadeDto q = new QuantidadeDto(2);
    		ClienteDto c = new ClienteDto("zeze");

    		AdicionarItemTabuleiroService aits = new AdicionarItemTabuleiroService(c, p, q);
    		aits.execute();

    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	}  	

    	

    	
    //11
		try {
    		committed = false;
    		Transaction.begin();
    		
    		PortalRestaurante rest = FenixFramework.getRoot();
			
    		Cliente c1 = rest.getClienteEspecifico("zeze");
    		Cliente c2 = rest.getClienteEspecifico("mariazinha");
    		
    		Compra compra1 = c1.getCompraPendente();
    		Compra compra2 = c2.getCompraPendente();
    		
    		if(compra1 == null)
    			throw new NotFoundException("O utilizador " + c1.getNome() + " não tem compras pendentes.");

    		for(Quantidade q : compra1.getQuantidade())
    			System.out.println("Utilizador: " + c1.getNome() + " Prato: " + q.getPrato().getNome() + " Quantidade: " + q.getQuantidade());
		
   			if(compra2 == null)
    			throw new NotFoundException("O utilizador " + c2.getNome() + " não tem compras pendentes.");

   			for(Quantidade q1 : compra2.getQuantidade())
    			System.out.println("Utilizador: " + c2.getNome() + " Prato: " + q1.getPrato().getNome() + " Quantidade: " + q1.getQuantidade());

		} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	} finally {
    		if (!committed)
    			Transaction.abort();
    	}
		
    	try {

    		ClienteDto c = new ClienteDto("zeze", "z3z3");

    		VerificarPasswordService aits = new VerificarPasswordService(c);
    		aits.execute();

    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	}
    	
    	try{
    		committed = false;
    		Transaction.begin();
    		
    		PortalRestaurante rest = FenixFramework.getRoot();
    		
    		Cliente c = rest.getClienteEspecifico("zeze");
    		Compra compra = c.getCompraPendente();
    		
    		if(compra == null)
    			throw new NotFoundException("O utilizador " + c.getNome() + " não tem compras pendentes.");
    		
    		for(Quantidade q : compra.getQuantidade())
    			System.out.println("Utilizador: " + c.getNome() + " Prato: " + q.getPrato().getNome() + " Quantidade: " + q.getQuantidade());
    	
    	} catch(RestException re){
    		System.out.println("Erro: " + re.getMessage());
    	} finally {
    		if (!committed)
    			Transaction.abort();
    	}
    }
}
