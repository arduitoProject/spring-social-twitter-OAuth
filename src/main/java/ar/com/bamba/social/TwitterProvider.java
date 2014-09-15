package ar.com.bamba.social;



import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
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


    /**
     * Metodo que a partir de credenciales de Twitter genera y devuelve un OAuthService
     * @author ptamburro
     */
	public OAuthService getOAuthService() {

		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.SSL.class)
				.apiKey(env.getProperty("bambaSpring.twitter.apiKey"))
				.apiSecret(env.getProperty("bambaSpring.twitter.apiSecret"))
				.callback(env.getProperty("bambaSpring.twitter.callbackUrl"))
				.build();

		return service;

	}
	
		
	/**
	 * Metodo que al recibir los access token de un usuario devuelve un
	 * TwitterTemplate
	 * 
	 * @author ptamburro
	 */
	public TwitterTemplate createTemplate(Usuario usuario) {
		if (usuario.getTwitterAccessToken() != null
				&& usuario.getTwitterSecret() != null) {
			return new TwitterTemplate(
					env.getProperty("bambaSpring.twitter.apiKey"),
					env.getProperty("bambaSpring.twitter.apiSecret"),
					usuario.getTwitterAccessToken(), usuario.getTwitterSecret());
		} else {
			return null;
		}
	}
}
