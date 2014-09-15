package ar.com.bamba.controller;


import java.io.IOException;
import java.util.Map;

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
	
	
	
	/**
	 * Rest que realiza la conexion con twitter para solicitar el RequestToken, 
	 * genero la url de autentificacion y hago el redirect
	 * 
	 * @author ptamburro
	 */
	@RequestMapping(value = "/oauth/twitter", method = RequestMethod.GET)
	public void requestConnectionToTwitter(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
	    //obtengo de twitter un requestToken
		OAuthService oauthService = twitterProvider.getOAuthService(); 
		Token requestToken = oauthService.getRequestToken();

		//seteo el token obtenido en la session para luego validarlo.
		HttpSession session=request.getSession();
		session.setAttribute(ATTRIBUTE_TOKEN_SESSION, requestToken);

	    //armo la url de autentificacion  (ejemplo: https://api.twitter.com/oauth/authorize?oauth_token=95neH6yH44Gmsz4cBbGxWvcBIpsIU1EU)
		String url =  oauthService.getAuthorizationUrl(requestToken);
	    
		//hago un redirect a la url
		response.sendRedirect(url);
	}
	
	
	/** 
	 * Callback URL.
	 * Este servicio es invocado por twitter cuando el usuario autoriza el acceso a nuestra app.
	 * Lo que hace es validar esta invocacion con el token seteado previamente en la session, obtiene los 
	 * access tokens del usuario y de esta forma el usr tiene acceso a la app.
	 * 
	 * @author ptamburro
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
		model.addObject("accesToken", accessToken.getToken());
		model.addObject("secretAccess", accessToken.getSecret());
		model.addObject("mensaje", "Pablito clavo un clavito, que clavito clavo pablito?");
		model.setViewName("acceso_ok");
		return model;
	 
		
		//todo armar una linda pantalla que permita meter texto y twittear llamando a un servicio
		
	}
	
	
	
	
	/**
	 * Servicio que permite generar tuits en diferentes cuentas por medio de la app. Obviamente
	 * es necesario contar con todos los tokens requeridos.
	 * 
	 * @author ptamburro
	 */
	@RequestMapping(value = "/post/twitter")
	public ModelAndView updateStatus(@RequestParam Map<String,String> params) {
	//public ModelAndView updateStatus(HttpServletRequest request, HttpServletResponse response) {
		
		
		String mensaje = params.get("mensaje");
		String access = params.get("accessToken");
		String secret = params.get("secretAccess");
		
		Usuario usuario = new Usuario(null, null, access, secret);		
		TwitterTemplate tp = twitterProvider.createTemplate(usuario);
		
		if(mensaje.length() > 140){
			System.out.println("Se trunca el mensaje por ser mayor a 140");
			mensaje = mensaje.substring(0, 140);
		}
		
		tp.timelineOperations().updateStatus(mensaje);
		
		
		//Manejo simple de paginas 
	   	ModelAndView model = new ModelAndView();
		model.addObject("title", "Bamba Spring Social Twitter");
		model.addObject("message", "Has publicado un mensaje en twitter por medio de la app");
		model.addObject("message_1", "Verifica el mensaje en tu cuenta de twitter");
		model.setViewName("pagina_generica");
		return model;
		
	}
	
}
