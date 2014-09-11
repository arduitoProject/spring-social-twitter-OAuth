package ar.com.bamba.controller;


import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import ar.com.bamba.entity.Usuario;
import ar.com.bamba.social.TwitterProvider;

@Controller
public class SocialController {

	@Autowired
	TwitterProvider  twitterProvider;
	
	
	
	/** Send redirect to url in twitter for login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/connect/twitter", method = RequestMethod.GET)
	public String requestConnectionToTwitter(WebRequest request) {
		
	    // get request token
		
		OAuthService oauthService = twitterProvider.getOAuthService(); 
		
		Token requestToken = oauthService.getRequestToken();
		
		// store request token in session
	    request.setAttribute("twitter_request_token", requestToken, WebRequest.SCOPE_SESSION);
	    
	    return "redirect:" + twitterProvider.getAuthorizeUrl() + "?oauth_token=" + requestToken.getToken();
	}
	
	
	/** Callback from twitter on success login
	 * 
	 * @param verifier
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/callback/twitter", method = RequestMethod.GET, params = "oauth_token")
	public String authorizeTwitterCallback(@RequestParam(value = "oauth_verifier", defaultValue = "verifier") String verifier,WebRequest request) {
		
		// get request token from session
	    Token requestToken = (Token)request.getAttribute("twitter_request_token", WebRequest.SCOPE_SESSION);
	    
	    OAuthService oauthService = twitterProvider.getOAuthService();
	    
	    // get access token
	    Token accessToken = oauthService.getAccessToken(requestToken, new Verifier(verifier));
	    
	    //String userName = getCurrentUser().getName();
	    //userService.updateTwitterAuthentication(userName, accessToken.getToken(), accessToken.getSecret());
	    
	    Usuario usuario = new Usuario();
	    usuario.setTwitterAccessToken(accessToken.getToken());
	    usuario.setTwitterSecret(accessToken.getSecret());
	    
	    return "redirect:/status";
	 
	}
	
	

	
	
}
