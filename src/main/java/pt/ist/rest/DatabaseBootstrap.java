package pt.ist.rest;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.PortalRestaurante;

public class DatabaseBootstrap {

	  private static boolean notInitialized = true;

	  public synchronized static void init() {
	    if (notInitialized) {
	       	FenixFramework.initialize(new Config() {{
	    		domainModelPath="src/main/dml/domain.dml";
	            dbAlias = "//localhost:3306/restdb";
	            dbUsername = "rest";
	            dbPassword = "r3st";
	            rootClass=PortalRestaurante.class;
	    	}});
	    }
	    notInitialized = false;
	  }

	  public static void setup() {
	    try {
	      pt.ist.rest.RestSetup.populateDomain();
	    } catch (pt.ist.rest.exception.RestException ex) {
	      System.out.println("Erro a fazer populate: " + ex);
	    }
	  }

}
