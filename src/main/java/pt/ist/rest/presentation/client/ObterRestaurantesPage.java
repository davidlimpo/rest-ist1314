package pt.ist.rest.presentation.client;

import java.util.List;

import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.RestauranteDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class ObterRestaurantesPage extends Composite {

	private RestServletAsync rpcService;
	private RestGWT rootPage;
	private ClienteDto loggedClient;
	
	public ObterRestaurantesPage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rootPage = rootPage;
		this.rpcService = rpcService;
	}
	
	public void showPage(ClienteDto loggedClient){
		this.loggedClient = loggedClient;
	    rpcService.obterRestaurantes(new AsyncCallback<List<RestauranteDto>>() {
			
			@Override
			public void onSuccess(List<RestauranteDto> result) {
				listRestaurantes(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {	
				Window.alert("Erro GWT");
			}
		});
	}
	
	public final void listRestaurantes(List<RestauranteDto> restaurantes){
		RootPanel.get("loginContainer").clear();
		
	    FlexTable flexTable = new FlexTable();
	    
		int i = 0;
	    for(final RestauranteDto r : restaurantes){
		    Button rest = new Button(r.getNome() + " " + r.getMorada() + " " + r.getClassificacao());
		    
		    flexTable.setWidget(i, 0, rest);
		    i++;
		    
		    rest.addClickHandler(new ClickHandler() {
				
				@Override		
				public void onClick(ClickEvent event) {
					rootPage.showMostrarMenuPage(loggedClient, r);
				}
			});
	    }
	
	    
	    initWidget(flexTable);

		RootPanel.get("loginContainer").add(this);
	}
}
