package pt.ist.rest.presentation.client;

import java.util.List;

import pt.ist.rest.service.dto.PratoDto;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class PratoNomeListPage extends Composite {

	
	private RestServletAsync rpcService;
	
	public PratoNomeListPage(final RestGWT rootPage, final RestServletAsync rpcService){
	    
		this.rpcService = rpcService;
	}
	
	public void showPage(String nomePrato){
		
	    FlexTable flexTable = new FlexTable();
		
	    rpcService.procurarPratosNome(nomePrato, new AsyncCallback<List<PratoDto>>() {
			
			@Override
			public void onSuccess(List<PratoDto> result) {
				listPratos(result);				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro GWT");				
			}
		});
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
		
	}
	
	
	public void listPratos(List<PratoDto> pratos) {		
		RootPanel.get("loginContainer").clear();
		
	    FlexTable flexTable = new FlexTable();
	    
	    Label pratoLbl = new Label("Prato: ");
	    flexTable.setWidget(0, 0, pratoLbl);
				
	    int i = 1;
	    for(final PratoDto pDto : pratos){
		    Label prato = new Label(pDto.getNome() + " " + pDto.getCalorias() + " " + pDto.getPreco() + " " + pDto.getClassificacao());
		    flexTable.setWidget(i, 0, prato);
		    		    
		    i++;
	    }
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);		
	}
}
