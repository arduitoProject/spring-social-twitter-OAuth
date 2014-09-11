package ar.com.bamba.service;

import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

import ar.com.bamba.domain.TwitterAppCredentials;

public interface TwitterOauthService{

	public OAuthService getOAuthService(String apiKey, String apiSecret);
	
	public TwitterAppCredentials getKeys(); 
	
}
