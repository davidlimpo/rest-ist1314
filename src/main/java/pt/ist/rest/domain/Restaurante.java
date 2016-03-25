package pt.ist.rest.domain;
import pt.ist.rest.exception.*;

public class Restaurante extends Restaurante_Base {

	private final static int LIMITE_PRATOS = 15;
	
    public Restaurante(String nome, String morada) {
    	this.setNome(nome);
        this.setMorada(morada);
    }
    
    public void adicionaPrato(Prato prato, Gestor gestor) throws RestException{
    	if(this.hasGestor(gestor)){
	    	if(this.getPrato().size() <= LIMITE_PRATOS)
	    		this.addPrato(prato);
	    	 else 
	    		throw new LimitedExceededException("O Restaurante " + this.getNome() + " excedeu o numero de pratos.");
    	}
    	else
    		throw new WrongManagerException("O gestor " + gestor.getNome() + " nao gere o restaurante " + this.getNome());
    }
    
    public void removePrato(Prato prato, Gestor gestor) throws RestException{
    	
    	if(this.hasGestor(gestor)){
	    	if(this.getPrato().size() <= LIMITE_PRATOS)
	    		this.removePrato(prato);
	    	 else 
	    		 throw new LimitedExceededException("O Restaurante " + this.getNome() + " excedeu o numero de pratos.");
	    }
    	else
			throw new WrongManagerException("O gestor " + gestor.getNome() + " nao gere o restaurante " + this.getNome());
    }
    
    private float calcClassificacao(){
    	float somaClassificacoes = 0;
    	
    	for(Prato p: this.getPrato())
    		somaClassificacoes += p.getClassificacao();
    	
    	float classificacaoRestaurante = somaClassificacoes / (float)this.getPrato().size();
    	
    	return classificacaoRestaurante;
    } 
    
    public float getClassificacao(){
    	
    	int MINIMO_PRATOS = 3;	
    	int pratoComLike = 0;
    	
    	for(Prato p : this.getPrato()){
    		if(p.getClassificacao() >= 1)
    			pratoComLike++;
    	}
    	
    	if(pratoComLike >= MINIMO_PRATOS)
    		return this.calcClassificacao();
		   	
    	else 
    		return 0;
    }
    
    public Prato getPratoEspecifico(String nome) throws NotFoundException {

    	for(Prato p: this.getPrato())
    		if(p.getNome().equals(nome))
    			return p;
    		
    	throw new NotFoundException("Não existe o prato " + nome);
    }
    
    @Override
    public void setPortalRestaurante(PortalRestaurante novoPortal){
    	novoPortal.addRestaurante(this);    
    }
    
    public void listarMenu(){
    	
		for (Prato p : this.getPrato())
			System.out.println("Nome: " + p.getNome() + ". Calorias: " + p.getCalorias()+ ". Classificação: " + p.getClassificacao());
    }    
    
}
