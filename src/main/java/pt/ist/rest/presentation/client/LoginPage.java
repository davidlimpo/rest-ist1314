package pt.ist.rest.presentation.client;
import pt.ist.rest.exception.RestException;
import pt.ist.rest.service.dto.ClienteDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPage extends Composite {
	private Button btnLogin;
	private TextBox textBox;
	private PasswordTextBox textBox2;
	
	public LoginPage(final RestGWT rootPage, final RestServletAsync rpcService){
	    FlexTable flexTable = new FlexTable();
		
		Label lblClient = new Label("Cliente:");
	    flexTable.setWidget(0, 0, lblClient);
	    lblClient.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    
	    Label lblPassword = new Label("Password:");		
	    flexTable.setWidget(0, 1, lblPassword);
	    lblPassword.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    
	    textBox = new TextBox();
	    flexTable.setWidget(1, 0, textBox);
	    
	    textBox2 = new PasswordTextBox();
	    flexTable.setWidget(1, 1, textBox2);
	    
	    btnLogin = new Button("login");
	    flexTable.setWidget(1, 2, btnLogin);
	    
	    btnLogin.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		doLogin(rootPage, rpcService);
	    	}
	    });
	    
	    initWidget(flexTable);
	}
	
	
	private final void doLogin(final RestGWT rootPage, final RestServletAsync rpcService) {

		// if any of the fields is empty, show warning:
	    if (this.textBox.getText().equals("")){
	      Window.alert("Please fill the name of the person!");
	      return;
	    }
	    
	    final ClienteDto cDto = new ClienteDto(this.textBox.getText(), this.textBox2.getText());
	    
    	rpcService.login(cDto, new AsyncCallback<Void>() {	
    		

    		@Override
			public void onSuccess(Void result) {
				rootPage.showIndexPage(cDto);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof RestException){
					Window.alert(caught.getMessage());
				}	
				else Window.alert("Erro GWT");
			}
		});    
	  }
}