package pt.ist.rest.presentation.client;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Composite;

import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.AlimentoDto;
import pt.ist.rest.service.dto.PratoDto;

public class ProcurarPratoTipoPage extends Composite {

	private RestServletAsync rpcService;
	private TextBox tipoPratoBox;
	private Button pesquisarButton;
	private Button carneButton;
	private Button peixeButton;
	private Button vegetarianoButton;
	private Label lblNome;
	
	FlexTable flexTable = new FlexTable();
	
	public ProcurarPratoTipoPage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rpcService = rpcService;
		
		lblNome = new Label("Tipo do prato:");
		tipoPratoBox = new TextBox();
		pesquisarButton = new Button("Pesquisar");
		carneButton = new Button("carne");
		peixeButton = new Button("peixe");
		vegetarianoButton = new Button("vegetariano");
		
		/*pesquisarButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("loginContainer").clear();
				doSearch(rootPage);
			}
		});*/
		
		carneButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("loginContainer").clear();
				doSearch(rootPage, "carne");
			}
		});
		
		peixeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("loginContainer").clear();
				doSearch(rootPage, "peixe");
			}
		});

		vegetarianoButton.addClickHandler(new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("loginContainer").clear();
				doSearch(rootPage, "vegetariano");
			}
		});

		
	}
	
	private final void doSearch(final RestGWT rootPage, String tipo) {
		
		AlimentoDto aDto = new AlimentoDto(tipo);
		
		rpcService.procurarPratosTipo(aDto, new AsyncCallback<List<PratoDto>>() {
		
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
		flexTable.setWidget(0, 0, carneButton);
	    flexTable.setWidget(1, 0, peixeButton);
	    flexTable.setWidget(2, 0, vegetarianoButton);
		
	    initWidget(flexTable);
	    
		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
	}
}
