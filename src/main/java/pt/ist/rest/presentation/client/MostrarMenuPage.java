package pt.ist.rest.presentation.client;

import java.util.List;

import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.RestauranteDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MostrarMenuPage extends Composite {

	private RestServletAsync rpcService;
	private ClienteDto loggedClient;
	
	public MostrarMenuPage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rpcService = rpcService;
	}
	
	public void showPage(ClienteDto loggedClient, RestauranteDto restaurante){		
		this.loggedClient = loggedClient;
		
		rpcService.mostrarMenu(restaurante, new AsyncCallback<List<PratoDto>>() {
			
			@Override
			public void onSuccess(List<PratoDto> result) {
				listPratos(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro GWT");
			}
		});
	}
	
	public void listPratos(List<PratoDto> pratos) {		
		RootPanel.get("loginContainer").clear();
		
	    FlexTable flexTable = new FlexTable();
	    
	    Label pratoLbl = new Label("Prato: ");
	    flexTable.setWidget(0, 0, pratoLbl);
		
		
		Label quantidadeLbl = new Label("Quantidade: ");
	    flexTable.setWidget(0, 1, quantidadeLbl);
		
	    int i = 1;
	    for(final PratoDto pDto : pratos){
		    Label prato = new Label(pDto.getNome() + " " + pDto.getCalorias() + " " + pDto.getPreco() + " " + pDto.getClassificacao());
		    flexTable.setWidget(i, 0, prato);
		    
		    
		    final TextBox t = new TextBox();
		    flexTable.setWidget(i, 1, t);
		    
		    Button b = new Button("Adicionar");
		    
		    b.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
					QuantidadeDto qDto = new QuantidadeDto(Integer.parseInt(t.getText()));			
					
					rpcService.adicionarPrato(loggedClient, pDto, qDto, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Adicionado com Sucesso.");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							if(caught instanceof NotFoundException){
								Window.alert(caught.getMessage());
							}	
							else Window.alert("Erro GWT");
						}
					});				
				}
			});
		    
		    flexTable.setWidget(i, 2, b);
		    
		    i++;
		    
	    }
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);		
	}
}
