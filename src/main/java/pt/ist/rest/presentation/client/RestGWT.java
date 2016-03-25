package pt.ist.rest.presentation.client;

import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.RestauranteDto;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RestGWT implements EntryPoint {
	
	private static final String remoteServerType = "remote";
	private static final String localServerType = "local";
	public static boolean remote = true;
	
	private final RestServletAsync rpcService = GWT.create(RestServlet.class);
	
	private LoginPage loginPage;
	private IndexPage indexPage;
	private MostrarMenuPage mostrarMenuPage;
	private MostrarTabuleiroPage mostrarTabuleiroPage;
	private ObterRestaurantesPage obterRestPage;
	private ProcurarPratoPage procurarPratoPage;
	private ProcurarPratoNomePage procurarPratoNomePage;
	private ProcurarPratoTipoPage procurarPratoTipoPage;
	
	private final Label serverTypeLabel = new Label("Rest");
	

	@Override
	public void onModuleLoad() {
		String serverType;
		
		if(RootPanel.get(remoteServerType) != null){
			serverTypeLabel.setText("Rest - Remote mode");
			serverType= remoteServerType;
			remote = true;
		}else{
			serverTypeLabel.setText("Rest - Local mode");
			serverType = localServerType;
			remote = false;
		}
		RootPanel typeRootPanel = RootPanel.get(serverType);
		typeRootPanel.add(serverTypeLabel);
		serverTypeLabel.setWidth("100%");
		
		this.rpcService.initServer(serverType, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
				Window.alert("Erro GWT");
			}
		});
		
		loginPage = new LoginPage(this, rpcService);
		indexPage = new IndexPage(this, rpcService);
		obterRestPage = new ObterRestaurantesPage(this, rpcService);
		mostrarMenuPage = new MostrarMenuPage(this, rpcService);
		mostrarTabuleiroPage = new MostrarTabuleiroPage(this, rpcService);
		procurarPratoPage = new ProcurarPratoPage(this, rpcService);
		procurarPratoNomePage = new ProcurarPratoNomePage(this, rpcService);
		procurarPratoTipoPage = new ProcurarPratoTipoPage(this, rpcService);

		showLoginPage();
	}
	
	void showLoginPage() {
		RootPanel.get("loginContainer").clear();
		RootPanel.get("loginContainer").add(loginPage);
	}
	
	void showIndexPage(ClienteDto loggedClient) {
		RootPanel.get("loginContainer").clear();
		indexPage.showPage(loggedClient);
	}
	
	void showObterRestPage(ClienteDto loggedClient){
		RootPanel.get("loginContainer").clear();
		obterRestPage.showPage(loggedClient);
	}
	
	void showMostrarMenuPage(ClienteDto loggedClient, RestauranteDto restaurante){
		RootPanel.get("loginContainer").clear();
		mostrarMenuPage.showPage(loggedClient, restaurante);
	}
	
	void showMostrarTabuleiroPage(ClienteDto loggedClient){
		RootPanel.get("loginContainer").clear();
		mostrarTabuleiroPage.showPage(loggedClient);
	}
	void showProcurarPratoPage(){
		RootPanel.get("loginContainer").clear();
		procurarPratoPage.showPage();
	}
	void showProcurarPratoNome(){
		RootPanel.get("loginContainer").clear();
		procurarPratoNomePage.showPage();
	}
	
	void showProcurarPratoTipo(){
		RootPanel.get("loginContainer").clear();
		procurarPratoTipoPage.showPage();
	}
	
}
