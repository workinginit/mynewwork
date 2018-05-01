package org.vermeg.services;

import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;

public class ConnexionSonarService {

	private static WsClient wsClient = null;
	
	
	private ConnexionSonarService(String url, String userName, String password, String token) {

		HttpConnector httpConnector = HttpConnector.newBuilder().url(url).credentials(userName, password).token(token).build();
	    wsClient = WsClientFactories.getDefault().newClient(httpConnector);
	}
	
	public static WsClient getInstance(String url, String userName, String password, String token) {
		if (wsClient==null) 
		{
			new ConnexionSonarService(url, userName, password, token);			
		}
		   return wsClient;
	}

}
