package pt.ist.rest.presentation.client;

import java.util.List;

import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.PratoDto;

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

public class ProcurarPratoNomePage extends Composite{
	
	private RestServletAsync rpcService;
	private TextBox nomePratoBox;
	private Button pesquisarButton;
	private Label lblNome;
	
	FlexTable flexTable = new FlexTable();
	
	public ProcurarPratoNomePage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rpcService = rpcService;
		
		lblNome = new Label("Nome do prato:");
		nomePratoBox = new TextBox();
		pesquisarButton = new Button("Pesquisar");
		
		pesquisarButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("loginContainer").clear();
				doSearch(rootPage);
			}
		});
		
	}
		
	private final void doSearch(final RestGWT rootPage) {
	    
		rpcService.procurarPratosNome(nomePratoBox.getText(), new AsyncCallback<List<PratoDto>>() {
		
			@Override
			public void onSuccess(List<PratoDto> result) {
				listPratos(result);				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof RestException)
					Window.alert("Erro do service");
				else Window.alert("Erro GWT");				
			}
		});
	}

	public void listPratos(List<PratoDto> pratos) {
		
		flexTable.clear();
		
	    Label pratoLbl = new Label("Prato: ");
	    //FlexTable flexTable = new FlexTable();
	    flexTable.setWidget(0, 0, pratoLbl);
				
	    int i = 1;
	    for(final PratoDto pDto : pratos){
		    Label prato = new Label(pDto.getNome() + " " + pDto.getCalorias() + " " + pDto.getPreco() + " " + pDto.getClassificacao());
		    flexTable.setWidget(i, 0, prato);	    
		    i++;
	    }
	    
		//initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
	}
		
	public void showPage(){
				
	    //FlexTable flexTable = new FlexTable();
		flexTable.setWidget(0, 0, lblNome);
	    flexTable.setWidget(0, 1, nomePratoBox);
	    flexTable.setWidget(0, 2, pesquisarButton);
		
	    initWidget(flexTable);
	    
		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
	}
}
