package pt.ist.rest.domain;

import pt.ist.rest.exception.*;

public class Cliente extends Cliente_Base {
	
	private final static int LIMITE_GOSTOS = 15;
		
	public Cliente(String nome, String username, String password, String morada, String email) {
        super();
    	init(nome, username, password);
        
    	this.setMorada(morada);
        this.setEmail(email);
	}
	
	public Cliente(String nome, String username, String password, String morada, String email, int nif) {
        super();
    	init(nome, username, password);
        
    	this.setMorada(morada);
        this.setEmail(email);
        this.setNif(nif);
    }
	
	public void likePrato(Prato prato) throws ClientLikeException{
		
		if(this.getPrato().size() <= LIMITE_GOSTOS){
			if(!this.hasPrato(prato)){
				this.addPrato(prato);
				prato.addCliente(this);
			}
			else 
				throw new ClientLikeException ("O cliente " + this.getNome() + " ja gosta do prato " + prato.getNome());
		}
		else 
			throw new ClientLikeException ("O cliente " + this.getNome() + " não pode gostar de mais pratos.");
	}
	
	public void unlikePrato(Prato prato) throws NotFoundException{
		
		if(this.hasPrato(prato)){
			this.removePrato(prato);
			prato.removeCliente(this);
		}
		else 
			throw new NotFoundException ("O prato " + prato.getNome() + " não existe na lista.");
	}
	
	public void alteraPrato(Prato adiciona, Prato remove) throws RestException{	
		unlikePrato(remove);
		likePrato(adiciona);
	}
	
	public Compra getCompraPendente(){
		
		for(Compra c: this.getCompra())
			if(!c.getPago())
				return c;
		
		return null;
	}

	public void encomendarPrato(Prato prato, int quantidade){
		
		Compra c = getCompraPendente();
		
		if(c == null)
			c = new Compra();

		Quantidade q = c.existePrato(prato);
		
		if(q == null){
			q = new Quantidade(quantidade);
			q.setPrato(prato);
			c.addQuantidade(q);	
		}
		else {
			q.incrementaQuantidade(quantidade);
		}
		
		if(q.getQuantidade() <= 0)
			c.removeQuantidade(q);
		
		float novoTotal = prato.getPreco() * quantidade; 
		c.actualizaPreco(novoTotal);
	}
	
	
	public void decrementaSaldo(float valorADecrementar){
		
		float novoSaldo = this.getSaldo() - valorADecrementar;
		this.setSaldo(novoSaldo);
		
	}
	
	@Override
	public void setSaldo(float novoSaldo) throws NegativeBalanceException {
		if(novoSaldo < 0)
    		throw new NegativeBalanceException("O saldo do cliente " + this.getNome() + " não pode ser negativo.");
		
		else super.setSaldo(novoSaldo);
	}
	
	public void efectuarPagamento(Compra c){
		
		float pagamento = c.getTotalPagar();
		
		if(!c.getPago())
			if(this.getSaldo() >= pagamento) {
				this.decrementaSaldo(pagamento);
				c.setPago(true);
			} else throw new NoMoneyException("O cliente " + this.getNome() + " não tem saldo.");
		else throw new NoPayBoardFoundException("O cliente " + this.getNome() + " não tem tabuleiro de compras."); 
	}

	public void somaSaldo(float valor){
		
		float novoValor = this.getSaldo() + valor;
		
		this.setSaldo(novoValor);
		
	}
	
    public void esvaziaTabuleiro(){
    	
    	Compra compra = this.getCompraPendente();
    	
    	if(compra != null)
    		this.removeCompra(compra);
    }
}