package pt.ist.rest.domain;

public class Compra extends Compra_Base {
	
    public Compra() {
    	this.setPago(false);
    	this.setTotalPagar(0);
    }
    
    
    public Quantidade existePrato(Prato p){
    	
    	for(Quantidade q: this.getQuantidade())
    		if(q.getPrato().getId() == p.getId())
    			return q;
    	
    	return null;
    }
    
    public void actualizaPreco(float preco){
    	float total = this.getTotalPagar();
    	total += preco;
    	this.setTotalPagar(total);
    }
}
