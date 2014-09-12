package ar.com.bamba.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.bamba.entity.Usuario;
import ar.com.bamba.social.TwitterProvider;

@Controller
public class SocialController {

	private static String ATTRIBUTE_TOKEN_SESSION = "attribute_token_session";
	
	@Autowired
	TwitterProvider  twitterProvider;
	
	
	
	/** Send redirect to url in twitter for login
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/connect/twitter", method = RequestMethod.GET)
	public void requestConnectionToTwitter(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
	    //obtengo de twitter un requestToken
		OAuthService oauthService = twitterProvider.getOAuthService(); 
		Token requestToken = oauthService.getRequestToken();

		//seteo el token obtenido en la session para luego validarlo.
		HttpSession session=request.getSession();
		session.setAttribute(ATTRIBUTE_TOKEN_SESSION, requestToken);

	    //armo la url de autentificacion
		String url =  oauthService.getAuthorizationUrl(requestToken);
	    
		//hago un redirect a la url
		response.sendRedirect(url);
	}
	
	
	/** 
	 * Callback URL
	 * Este servicio es invocado por twitter cuando el usuario autoriza el acceso a nuestra app
	 */
	@RequestMapping(value = "/callback/twitter", method = RequestMethod.GET, params = "oauth_token")
	public ModelAndView authorizeTwitterCallback(@RequestParam(value = "oauth_verifier", defaultValue = "verifier") String verifier, HttpServletRequest request, HttpServletResponse response) {
		
       //Obtengo el request token guardado en la session
	   Token requestToken = (Token) request.getSession().getAttribute(ATTRIBUTE_TOKEN_SESSION);
	 
	   //Obtengo el access Token luego de validar los tokens de la peticion
	   OAuthService oauthService = twitterProvider.getOAuthService();
	   Token accessToken = oauthService.getAccessToken(requestToken, new Verifier(verifier));
	   
	   System.out.println(">> Access Token recibido");
	   System.out.println(">> access_token : " + accessToken.getToken());
	   System.out.println(">> access_secret : " + accessToken.getSecret());
	   
	   
	   //TODO Aca se deberian asociar estos tokens con los usuarios (por ej persistiendolos)
	   
	   
	   //Manejo simple de paginas 
	   	ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Social Twitter");
		model.addObject("message", "Has publicado un mensaje en twitter por medio de esta app");
		model.setViewName("hello");
		return model;
	 
		
		//todo armar una linda pantalla que permita meter texto y twittear llamando a un servicio
		
	}
	
	
	public static void main(String[] args) {
		   
		   Usuario usuario = new Usuario("bambaSpring", "nose", "2786148523-2qamrro8uc8T508TGoBTCnOMPszEhd0kFFxJUaf", "EbK667Z1UCqrW9EKWtccl6VOiAyAimU8zCPndx2mq79iu");
		
		String consumerKey = "6rjiEAcIfxOGKMT0FTl2KlPKn";
		String consumerSecret =  "VjVDs5tvNXzSJKp1Y8F5wcpQDuy8Fukrzm6wcX0x7bgHqp0e1I";
				String accessToken = "XXX";
				String accessTokenSecret =  "XXXX";
		
		  TwitterTemplate tp = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		
		  tp.timelineOperations().updateStatus("pablito clavo un clavito, que clavito clavo pablito?");
		
	}
	
	
}
