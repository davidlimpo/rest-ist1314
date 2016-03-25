package pt.ist.rest.domain;

import java.util.Set;


//Aqui queremos ir buscar o id do portal mas nao queremos que o prato possa alterar o mesmo

public class Prato extends Prato_Base {
    
	public Prato(PortalRestaurante portal, String nome, Set<Alimento> alimentos) {
		
		this.setPortalRestaurante(portal);
		this.setNome(nome);
		this.setId(portal.getNextPratoId());
		
		for(Alimento a: alimentos)
        	this.addAlimento(a);
	}
	
    public Prato(PortalRestaurante portal, String nome, int preco, int calorias, Set<Alimento> alimentos) {
    	
        this.setPortalRestaurante(portal);
    	this.setNome(nome);
        this.setPreco(preco);
        this.setCalorias(calorias);
        this.setId(portal.getNextPratoId());
        
        for(Alimento a: alimentos)
        	this.addAlimento(a);
    }
    
    public int getClassificacao() {
    	return this.getCliente().size();
    }
    
    public boolean vegetariano(){
    	for(Alimento a : this.getAlimento())
    		if(a instanceof Carne || a instanceof Peixe)
    			return false;
    	return true;
    }
    
    public boolean tipoCarne(){
    	for(Alimento a : this.getAlimento())
    		if(a instanceof Carne)
    			return true;
    	return false;
    }
    
    public boolean tipoPeixe(){
    	for(Alimento a : this.getAlimento())
    		if(a instanceof Peixe)
    			return true;
    	return false;
    }
}

