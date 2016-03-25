package pt.ist.rest.presentation.client;

import pt.ist.rest.exception.NotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.QuantidadeDto;
import pt.ist.rest.service.dto.TabuleiroDto;

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

public class MostrarTabuleiroPage extends Composite {

	private RestServletAsync rpcService;
	private ClienteDto loggedClient;
	
	public MostrarTabuleiroPage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rpcService = rpcService;
	}
	
	public void showPage(ClienteDto loggedClient){
		
		this.loggedClient = loggedClient;
		
		rpcService.mostrarTabuleiro(loggedClient, new AsyncCallback<TabuleiroDto>() {
			
			@Override
			public void onSuccess(TabuleiroDto result) {
				listTabuleiro(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof NotFoundException)
					Window.alert(caught.getMessage());
				else Window.alert("Erro GWT");
				
			}
		});
	}
	
	public final void listTabuleiro(TabuleiroDto tabuleiro){

		RootPanel.get("loginContainer").clear();

	    FlexTable flexTable = new FlexTable();

    	Label l1 = new Label("Custo actual tabuleiro:");
    	Label l2 = new Label(""+tabuleiro.getCusto());
    	Label l3 = new Label("Saldo do cliente:");
    	Label l4 = new Label(""+tabuleiro.getSaldo());

	    flexTable.setWidget(0, 0, l1);
	    flexTable.setWidget(1, 0, l2);
	    flexTable.setWidget(0, 1, l3);
	    flexTable.setWidget(1, 1, l4);

		int i = 2;
	    for(final PratoDto p : tabuleiro.getListPratoDto()){
	    	Label l5 = new Label("Nome:");
	    	Label l6 = new Label(p.getNome());
	    	Label l7 = new Label("Preco: ");
	    	Label l8 = new Label(""+p.getPreco());
	    	Label l9 = new Label("Identificador: ");
	    	Label l10 = new Label(""+p.getId());
	    	Label l11 = new Label("Quantidade: ");
	    	Label l12 = new Label(""+p.getQuantidadeDto().getQuantidade());
	    	Label l13 = new Label("Nova Quantidade:");

	    	final TextBox quantidadeBox = new TextBox();
	    	Button addButton = new Button("Alterar quantidade");
	    	
	    	
	    	addButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
			    	QuantidadeDto qDto = new QuantidadeDto(Integer.parseInt(quantidadeBox.getText()));

					rpcService.alterarQuantidade(loggedClient, p, qDto, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Alterado com sucesso");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							if(caught instanceof NotFoundException)
								Window.alert(caught.getMessage());
							else Window.alert("Erro GWT");
						}
					});
				}
			});
	    	
	        flexTable.setWidget(i, 0, l5);
		    flexTable.setWidget(i, 1, l6);
		    flexTable.setWidget(i, 2, l7);
		    flexTable.setWidget(i, 3, l8);
		    flexTable.setWidget(i, 4, l9);
		    flexTable.setWidget(i, 5, l10);
		    flexTable.setWidget(i, 6, l11);
		    flexTable.setWidget(i, 7, l12);
		    
		    flexTable.setWidget(i, 8, l13);
		    flexTable.setWidget(i, 9, quantidadeBox);
		    flexTable.setWidget(i, 10, addButton);
		    
		    i++;
	    }    
	    
	    Button pagar = new Button("Pagar");
	    pagar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(RestGWT.remote){
					rpcService.pagarRemotoTabuleiro(loggedClient, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Pago com sucesso");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							if(caught instanceof NotFoundException)
								Window.alert(caught.getMessage());
							else Window.alert("Erro GWT");
						}
					});	
				}
				else{
					rpcService.pagarLocalTabuleiro(loggedClient, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Pago com sucesso");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							if(caught instanceof NotFoundException)
								Window.alert(caught.getMessage());
							else Window.alert("Erro GWT");
						}
					});
				}
			}
		} );
	    
	    flexTable.setWidget(++i, 0, pagar);
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
	}

}