package ar.com.bamba.domain;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;

public class TwitterOauth {

	private static final String AUTHORIZE_URL = "https://api.linkedin.com/uas/oauth/authorize?oauth_token=";
	
	private String apiKey;
	
	private String apiSecretKey;
	
	private OAuthService service;
	
	private Token requestToken;
	
	
	 public TwitterOauth(String apiKey, String apiSecret) {
		 
		   this.apiKey = apiKey;
		   this.apiSecretKey = apiSecret;
		   
		   this.service = new ServiceBuilder()
		                 .provider(TwitterApi.class)
		                 .apiKey(getApiKey())
		                 .apiSecret(getApiSecretKey())
		                 .callback("http://myserver.myapp.com:8080/myapp/liEndOAuth.htm")
		                 .build();
		   
		   requestToken = this.service.getRequestToken();
		 }

	 
	 
	 public String getAuthorizationURL() {
		   requestToken = service.getRequestToken();
		   return AUTHORIZE_URL + requestToken.getToken();
		 }
	
	 /*
	public AccessToken getOAuthAccessToken(String verifierCode) {
		Verifier verifier = new Verifier(verifierCode);
		Token token = service.getAccessToken(requestToken, verifier);
		AccessToken accessToken = new AccessToken(token.getToken(),
				token.getSecret());

		return accessToken;
	}
	 
	 */
	 
	 
	 
	 
	 
	 
	 
	 
    //Getters & Getters
	public String getApiKey() {
		return apiKey;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}


	public OAuthService getService() {
		return service;
	}


	public void setService(OAuthService service) {
		this.service = service;
	}


	public Token getRequestToken() {
		return requestToken;
	}


	public void setRequestToken(Token requestToken) {
		this.requestToken = requestToken;
	}

	public String getApiSecretKey() {
		return apiSecretKey;
	}

	public void setApiSecretKey(String apiSecretKey) {
		this.apiSecretKey = apiSecretKey;
	}
		
	
}
