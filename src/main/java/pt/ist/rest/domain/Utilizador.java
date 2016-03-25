package pt.ist.rest.domain;

public abstract class Utilizador extends Utilizador_Base {
      
    public void init(String nome, String username, String password) {
        setNome(nome);
        setUserName(username);
        setPassword(password);
    }
    
    @Override
    public void setPortalRestaurante(PortalRestaurante novoPortal){
    	novoPortal.addUtilizador(this);
    }
    
    public boolean verificaPassword(String pass){
    	if(this.getPassword().equals(pass))
    		return true;
    	else
    		return false;
    }
}
