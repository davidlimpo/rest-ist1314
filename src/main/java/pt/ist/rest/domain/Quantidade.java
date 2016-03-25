package pt.ist.rest.domain;

public class Quantidade extends Quantidade_Base {
    
    public Quantidade(int quantidade) {
    	setQuantidade(quantidade);
    }
    
    public void incrementaQuantidade(int incremento){
    	
    	int quantidade = this.getQuantidade();
    	this.setQuantidade(quantidade + incremento);
    }
}
