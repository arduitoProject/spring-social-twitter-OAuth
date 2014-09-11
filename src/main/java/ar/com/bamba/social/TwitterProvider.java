package ar.com.bamba.social;



import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Repository;

import ar.com.bamba.entity.Usuario;

@Repository
@PropertySource("classpath:/twitter.properties")
public class TwitterProvider {
		

    @Autowired
    private Environment env;


	public OAuthService getOAuthService() {
		
	    OAuthService service = new ServiceBuilder()
        .provider(TwitterApi.SSL.class)
        .apiKey(env.getProperty("twitter.apiKey"))
        .apiSecret(env.getProperty("twitter.apiSecret"))
        .callback(env.getProperty("twitter.callbackUrl"))
        .build();
	    
	    
	    return service;
		
	}
	
	public String getAuthorizeUrl() {
		return "https://api.twitter.com/oauth/authorize";
	}
	
	
	/** Factory method to create twitter template (request-scoped) for current user
	 * 
	 * @return
	 */
	public TwitterTemplate createTemplate(Usuario usuario) {
	    if (usuario.getTwitterAccessToken() != null) {
	    	return new TwitterTemplate(env.getProperty("twitter.apiKey"), env.getProperty("twitter.apiSecret"), usuario.getTwitterAccessToken(), usuario.getTwitterSecret());
	    } else {
	    	return null;
	    }
	}
}
