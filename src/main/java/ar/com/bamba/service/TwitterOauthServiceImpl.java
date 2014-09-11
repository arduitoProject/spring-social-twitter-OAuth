package ar.com.bamba.service;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;

import ar.com.bamba.domain.TwitterAppCredentials;

public class TwitterOauthServiceImpl implements TwitterOauthService {

	

	@Value("${bambaSpring.twitter.api.key}")
	private String apiKey_1;
	
	@Value("${bambaSpring.twitter.api.secret}")
	private String apiSecret_1;
		
	public OAuthService getOAuthService(String apiKey, String apiSecret){
		
		//String apiKey = "c97dc03050b89baf893347f40f177d55";
	    //String apiSecret = "c3301704fdd6da30b5b8ab68f9808b09";
	    OAuthService service = new ServiceBuilder()
	                                  .provider(TwitterApi.class)
	                                  .apiKey(apiKey)
	                                  .apiSecret(apiSecret)
	                                  .callback("http://localhost:8080/social/callback.jsp")
	                                  .build();
		
	    return service;
		
	}
	
//	public String getAuthorizationURL(){
//		
//		requestToken = service.getRequestToken();
//		return AUTHORIZE_URL + requestToken.getToken();
//		
//	}
	
	
	

	public TwitterAppCredentials getKeys() {
		
		TwitterAppCredentials twitterAppCredentials = new TwitterAppCredentials();
		twitterAppCredentials.setApiKey(apiKey_1);
		twitterAppCredentials.setApiSecret(apiSecret_1);
		
		return twitterAppCredentials;
	}
	
	
}
