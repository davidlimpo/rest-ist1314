package pt.ist.rest.presentation.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

import pt.ist.rest.service.dto.ClienteDto;

public class ProcurarPratoPage extends Composite {
	
	private final Button procuraTipo = new Button("Procurar por Tipo");
	private final Button procuraNome = new Button("Procurar por Nome");
	
	private RestServletAsync rpcService;
	
	public ProcurarPratoPage(final RestGWT rootPage, final RestServletAsync rpcService){
		this.rpcService = rpcService;
	
	
		procuraNome.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showProcurarPratoNome();
			}
		});
		
		procuraTipo.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showProcurarPratoTipo();
			}
		});
	}
	
	public void showPage(){
		
		FlexTable flexTable = new FlexTable();
	    flexTable.setWidget(0, 0, procuraTipo);
	    flexTable.setWidget(1, 0, procuraNome);
	    
	    initWidget(flexTable);

		RootPanel formRootPanel = RootPanel.get("loginContainer");
		formRootPanel.add(this);
		
	}
}
