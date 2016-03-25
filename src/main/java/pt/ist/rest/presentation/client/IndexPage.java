package pt.ist.rest.presentation.client;

import pt.ist.rest.service.dto.ClienteDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class IndexPage extends Composite {
	private final Button obterRestaurantes = new Button("Obter Restaurantes");
	private final Button mostrarTabuleiro = new Button("Mostrar Tabuleiro de Compras");
	private final Button procurarPrato = new Button("Procura Prato");
	private ClienteDto loggedClient;
	
	
	public IndexPage(final RestGWT rootPage, final RestServletAsync rpcService){
		
		obterRestaurantes.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showObterRestPage(loggedClient);
			}
		});
		
		mostrarTabuleiro.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showMostrarTabuleiroPage(loggedClient);
			}
		});
		
		procurarPrato.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showProcurarPratoPage();
			}
		});
	}
	
	public void showPage(ClienteDto loggedClient){
		this.loggedClient = loggedClient;
			
	    FlexTable flexTable = new FlexTable();
	    flexTable.setWidget(0, 0, obterRestaurantes);
	    flexTable.setWidget(1, 0, mostrarTabuleiro);
	    flexTable.setWidget(2, 0, procurarPrato);
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
	}

}
