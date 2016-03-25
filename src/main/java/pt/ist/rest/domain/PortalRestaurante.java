package pt.ist.rest.domain;

import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.registry.JAXRException;
import javax.xml.ws.BindingProvider;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;


import pt.ist.registofatura.RegistoFaturaLocal;
import pt.ist.registofatura.ws.ClienteInexistente_Exception;
import pt.ist.registofatura.ws.EmissorInexistente_Exception;
import pt.ist.registofatura.ws.Fatura;
import pt.ist.registofatura.ws.FaturaInvalida_Exception;
import pt.ist.registofatura.ws.ItemFatura;
import pt.ist.registofatura.ws.Serie;
import pt.ist.registofatura.ws.TotaisIncoerentes_Exception;
import pt.ist.rest.exception.AlreadyExistsException;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.uddi.UDDINaming;

public class PortalRestaurante extends PortalRestaurante_Base {
	
	private static final float txIVA = 0.23f;
	private static Serie s;
	private static final int MAXIMO_SERIES = 4;
	private static RegistoFaturaLocal registoLocal = new RegistoFaturaLocal();
	
    public  PortalRestaurante() {
    	super();
    }
    
    public PortalRestaurante(int nif, String nome){
    	super();
    	this.setNif(nif);
    	this.setNome(nome);
    	this.setPrecoMaximo(8);
    	this.setNumSequencia(0);
    	this.setNumSeriesRestantes(0);
    }
    
    @Override
    public void addUtilizador(Utilizador utilizador) throws AlreadyExistsException {
    	
    	for (Utilizador u : this.getUtilizador()) {
    		if(u.getUserName().equals(utilizador.getUserName()))
    			throw new AlreadyExistsException("O utilizador " + u.getNome() + " ja existe.");
    	}
    	super.addUtilizador(utilizador);
    }
    
    @Override
    public void addRestaurante(Restaurante restaurante) throws AlreadyExistsException {
    	for (Restaurante r : this.getRestaurante()) {
    		if(r.getNome().equals(restaurante.getNome()))
    			throw new AlreadyExistsException("O restaurante " + restaurante.getNome() + " ja existe.");
    	}
    	super.addRestaurante(restaurante);
    }
    
    public Utilizador getUtilizadorEspecifico(String username) throws NotFoundException {
    	
    	for(Utilizador u: this.getUtilizador())
    		if(u.getUserName().equals(username))
    			return u;
    	
    	throw new NotFoundException("Não existe o utilizador " + username);    
    }
    
    public Cliente getClienteEspecifico(String username) throws NotFoundException {
    	
    	for(Utilizador u: this.getUtilizador())
    		if(u.getUserName().equals(username))
    			if(u instanceof Cliente)
    				return (Cliente)u;
    	throw new NotFoundException("Não existe o cliente " + username);    
    }
    
    public Restaurante getRestauranteEspecifico(String nome) throws NotFoundException {
    	
    	for(Restaurante r: this.getRestaurante())
    		if(r.getNome().equals(nome))
    			return r;
    	
    	throw new NotFoundException("Não existe o restaurante " + nome);    
    }
    
    public Prato getPratoEspecifico(String nome) throws NotFoundException {
    	
    	for(Prato p: this.getPrato())
    		if(p.getNome().equals(nome))
    			return p;
    	
    	throw new NotFoundException("Não existe o prato " + nome);    
    }
    
    public Prato getPratoById(int id) throws NotFoundException {
    	
    	for(Prato p: this.getPrato())
    		if(p.getId() == id)
    			return p;
    	
    	throw new NotFoundException("Não existe o prato com identificador" + id);    
    }
    
    public int getNextPratoId(){;
    	int id = this.getIdPrato();
    	
    	this.setIdPrato(++id);
    	return id;
    }
    
    public void obterRestaurantes(){
    	
    		for (Restaurante r : this.getRestaurante()){
				System.out.print("Restaurante: " + r.getNome() + ". Morada: " + r.getMorada() + ". Classificação: " + r.getClassificacao());
    			
				System.out.println();
    		}  	
    }
    
    public void obterMenu(Restaurante r){
    	
		r.listarMenu();
    }
    
    
    public void gostarPrato(Cliente c, Prato p){
    	
    	c.likePrato(p);
    }
    
    public void actualizarSaldo(float valor, Cliente c){
    	
    	c.somaSaldo(valor);
    }
    
    public void procurarPratos(String nome){
    	   	
    	for (Restaurante r : this.getRestaurante())
    		for(Prato p : r.getPrato())
    			if(p.getNome().contains(nome))
    				System.out.println("Descrição: " + p.getNome() + ". Preço: " + p.getPreco() + ". Classificação: " + p.getClassificacao()
    						+ ". Calorias: " + p.getCalorias() + ". Restaurante: " + r.getNome());
    			else
    				System.out.println("Não encontrou o Prato indicado");
    }
    
    
    public void registaPagamento(String username){
    	
    	for(Utilizador u : this.getUtilizador())
    		if(u.getUserName().equals(username)){
    			Cliente c = (Cliente) u;
    			c.efectuarPagamento(c.getCompraPendente());	
    		}
    }
    
    public void registarFaturaLocal(Compra compra){
    	
    	if(!verificaSerie()){
			try {
				s = registoLocal.pedirSerie(1212);
				this.setNumSeriesRestantes(MAXIMO_SERIES);
				this.setNumSequencia(0);
				
			} catch (EmissorInexistente_Exception e1) {
				System.out.println(e1.getMessage());
			}
    	}
	
    	this.setNumSeriesRestantes(this.getNumSeriesRestantes()-1);
        
		try {
			GregorianCalendar gc = new GregorianCalendar();
	        XMLGregorianCalendar data;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
	    	Fatura f = new Fatura();
		   	ItemFatura itemFatura;
		   	 
		   	float totalIVA = 0;
		   	int totalPreco = 0;
		   	 
		   	for(Quantidade q : compra.getQuantidade()){
		   		itemFatura = new ItemFatura();
				itemFatura.setDescricao(q.getPrato().getNome());
				itemFatura.setPreco((int)q.getPrato().getPreco());
				itemFatura.setQuantidade(q.getQuantidade());
				f.getItens().add(itemFatura);
				totalIVA += q.getPrato().getPreco()*txIVA/(1+txIVA);
				totalPreco += q.getPrato().getPreco();
			}
		        
	       	f.setData(data);
	        f.setIva((int)totalIVA);
	        f.setNifCliente(compra.getCliente().getNif());
	        f.setNifEmissor(1212);
	        f.setNomeEmissor(this.getNome());
	        f.setNumSeqFatura(this.getNumSequencia());
	        f.setNumSerie(s.getNumSerie());
	        f.setTotal(totalPreco);
	        
	        this.setNumSequencia(this.getNumSequencia() + 1);
	        registoLocal.comunicarFatura(f);
	           
		} catch (DatatypeConfigurationException e1) {

		} catch (ClienteInexistente_Exception e2) {
			e2.printStackTrace();
		} catch (EmissorInexistente_Exception e3) {
			e3.printStackTrace();
		} catch (FaturaInvalida_Exception e4) {
			e4.printStackTrace();
		} catch (TotaisIncoerentes_Exception e5) {
			e5.printStackTrace();
		}  
	}
    
    public void registarFaturaRemoto(Compra compra) {
  /* 	 	String uddiURL = "http://localhost:8081";
        String name = "RegistoFatura";


	         System.out.printf("Contacting UDDI at %s%n", uddiURL);
	         UDDINaming uddiNaming = new UDDINaming(uddiURL);
	
	         System.out.printf("Looking for '%s'%n", name);
	         String endpointAddress = uddiNaming.lookup(name);
	         System.out.println("############### endpoint: " + endpointAddress);
	         
	         if (endpointAddress == null) {
	             System.out.println("Not found!");
	             return;
	         } else {
	             System.out.printf("Found %s%n", endpointAddress);
	         }
	
	         System.out.println("Creating stub ...");
	         pt.registofatura.ws.RegistoFaturaService service = new pt.registofatura.ws.RegistoFaturaService();
	         pt.registofatura.ws.RegistoFaturaPortType port = service.getRegistoFaturaPort();
	
	         System.out.println("Setting endpoint address ...");
	         BindingProvider bindingProvider = (BindingProvider) port;
	         Map<String, Object> requestContext = bindingProvider.getRequestContext();
	         requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
        

     
        
        
        if(!verificaSerie()){
			try {
				s = registoLocal.pedirSerie(1212);
				this.setNumSeriesRestantes(MAXIMO_SERIES);
				this.setNumSequencia(0);
				
			} catch (EmissorInexistente_Exception e1) {
				System.out.println(e1.getMessage());
			}
    	}
	
    	this.setNumSeriesRestantes(this.getNumSeriesRestantes()-1);
        
		try {
			GregorianCalendar gc = new GregorianCalendar();
	        XMLGregorianCalendar data;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
	    	Fatura f = new Fatura();
		   	ItemFatura itemFatura;
		   	 
		   	float totalIVA = 0;
		   	int totalPreco = 0;
		   	 
		   	for(Quantidade q : compra.getQuantidade()){
		   		itemFatura = new ItemFatura();
				itemFatura.setDescricao(q.getPrato().getNome());
				itemFatura.setPreco((int)q.getPrato().getPreco());
				itemFatura.setQuantidade(q.getQuantidade());
				f.getItens().add(itemFatura);
				totalIVA += q.getPrato().getPreco()*txIVA/(1+txIVA);
				totalPreco += q.getPrato().getPreco();
			}
		        
	       	f.setData(data);
	        f.setIva((int)totalIVA);
	        f.setNifCliente(compra.getCliente().getNif());
	        f.setNifEmissor(1212);
	        f.setNomeEmissor(this.getNome());
	        f.setNumSeqFatura(this.getNumSequencia());
	        f.setNumSerie(s.getNumSerie());
	        f.setTotal(totalPreco);
	        
	        this.setNumSequencia(this.getNumSequencia() + 1);
	        registoLocal.comunicarFatura(f);

		} catch (DatatypeConfigurationException e1) {

		} catch (ClienteInexistente_Exception e2) {
			e2.printStackTrace();
		} catch (EmissorInexistente_Exception e3) {
			e3.printStackTrace();
		} catch (FaturaInvalida_Exception e4) {
			e4.printStackTrace();
		} catch (TotaisIncoerentes_Exception e5) {
			e5.printStackTrace();
		}
        */
   }
    
    public boolean verificaSerie(){
    	if(this.getNumSeriesRestantes() == 0)
    		return false;
    	else return true;
    }
}
