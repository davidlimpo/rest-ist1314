package pt.ist.rest; 


import java.util.HashSet;
import java.util.Set;

import jvstm.Atomic;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Alimento;
import pt.ist.rest.domain.Carne;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Gestor;
import pt.ist.rest.domain.Peixe;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Vegetariano;
	  	  
	  
public class RestSetup {

    public static void main(String[] args) {
	FenixFramework.initialize(new Config() {{
	    domainModelPath="src/main/dml/domain.dml";
            dbAlias = "//localhost:3306/restdb";
            dbUsername = "rest";
            dbPassword = "r3st";
            domainModelPath="src/main/dml/domain.dml";
	    rootClass=PortalRestaurante.class;
	    
	}});
	populateDomain();
    }
    
    @Atomic
    public static void populateDomain() {
    	PortalRestaurante rest = FenixFramework.getRoot();
   

		Gestor g1 = new Gestor("Passos Lebre", "mng", "nm8");
		Gestor g2 = new Gestor("Paulo Portão", "pp", "pp");
		  
		Cliente c1 = new Cliente("Zé Ninguém", "zeze", "z3z3", "Lisboa, Portugal", "ze.ninguem@ist.utl.pt", 1111);
		Cliente c2 = new Cliente("Maria Silva", "mariazinha" , "****", "Porto, Portugal", "maria.silva@ist.utl.pt", 2222);
		Cliente c3 = new Cliente("Alice Alves", "alice" , "aaa", "Aveiro, Portugal", "alice.alves@sonet.pt", 1001);
		Cliente c4 = new Cliente("Bruno Bento", "bruno" , "bbb", "Beja, Portugal", "bruno.bento@sonet.pt", 1002);
		Cliente c5 = new Cliente("Carlos Calado", "carlos" , "ccc", "Coimbra, Portugal", "carlos.calado@sonet.pt", 1003);
		
    	Restaurante r1 = new Restaurante("Barriga Cheia", "Lisboa, Portugal");
    	Restaurante r2 = new Restaurante("Barriga Feliz", "Lisboa, Portugal");
    	
    	Peixe peixe1 = new Peixe("Bacalhau");
    	Peixe peixe2 = new Peixe("Bacalhau");
    	Vegetariano vegetariano1 = new Vegetariano("batatas");
    	Vegetariano vegetariano2 = new Vegetariano("batatas");
    	Carne carne1 = new Carne("Bife");
    	Carne carne2 = new Carne("Galinha");
    	
    	Set<Alimento> alimentosPrato1 = new HashSet<Alimento>();
    	alimentosPrato1.add(peixe1);
    	alimentosPrato1.add(vegetariano1);
    	
    	Set<Alimento> alimentosPrato2 = new HashSet<Alimento>();
    	alimentosPrato2.add(carne1);
    	alimentosPrato2.add(vegetariano1);

    	Set<Alimento> alimentosPrato3 = new HashSet<Alimento>();
    	alimentosPrato3.add(carne2);

    	Set<Alimento> alimentosPrato4 = new HashSet<Alimento>();
    	alimentosPrato4.add(peixe2);
    	alimentosPrato4.add(vegetariano2);
    	
    	Prato prato1 = new Prato(rest, "Bacalhau com batatas", 12, 350, alimentosPrato1);
    	Prato prato2 = new Prato(rest, "Bitoque", 7, 550, alimentosPrato2);
    	Prato prato3 = new Prato(rest, "Canja de galinha", 3, 120, alimentosPrato3);
    	Prato prato4 = new Prato(rest, "Bacalhau com batatas", 9, 220, alimentosPrato4);
    	
    	
    	try {
    		rest.addUtilizador(g1);
			rest.addUtilizador(g2);
			rest.addUtilizador(c1);
			rest.addUtilizador(c2);
			
			c1.setSaldo(99999);
			
			r1.addGestor(g1);
			r2.addGestor(g2);
						
			r1.adicionaPrato(prato1, g1);
			r2.adicionaPrato(prato4, g2);
			
			r2.adicionaPrato(prato2, g2);
			r2.adicionaPrato(prato3, g2);
			
			rest.addRestaurante(r1);
			rest.addRestaurante(r2);
			
			
    	}catch(Exception e){
    		System.out.println("Erro: " + e.getMessage());
    	}
    }
}
