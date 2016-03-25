package pt.ist.rest.service;

import javax.xml.registry.JAXRException;

import jvstm.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.PortalRestaurante;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.domain.Restaurante;
import pt.ist.rest.domain.Utilizador;
import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.exception.RestException;

public abstract class PortalRestauranteService {

	@Atomic
	public void execute() throws RestException {
		dispatch();
	}
	
	public abstract void dispatch() throws RestException;
	
	protected Prato getPrato(int id) {
		PortalRestaurante portal = FenixFramework.getRoot();
		
		try {
			Prato prato = portal.getPratoById(id);
			return prato;
		}
		catch (NotFoundException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Cliente getCliente(String username) {
		PortalRestaurante portal = FenixFramework.getRoot();
		
		try {
			Utilizador utilizador = portal.getUtilizadorEspecifico(username);
			if(utilizador instanceof Cliente)
				return (Cliente)utilizador;
		}
		catch (NotFoundException e){
			System.out.println(e.getMessage());
		}
		return null;
	}	
	
	public Restaurante getRestaurante(String nome) {
		PortalRestaurante portal = FenixFramework.getRoot();
		
		try {
			Restaurante r = portal.getRestauranteEspecifico(nome);
			return r;
		}
		catch (NotFoundException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
}
