package ar.com.bamba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AccesoController {

	
	@RequestMapping(value="/admin", method = RequestMethod.GET )
	public ModelAndView adminPage(){
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Ejemplo simple de Spring Security");
		model.addObject("message", ">> PAGINA PROTEGIDA <<");
		model.setViewName("admin");
 
		return model;
		
	}
	
	
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Ejemplo simple de Spring Security");
		model.addObject("message", "Bienvenido!!");
		model.setViewName("pagina_generica");
		return model;
 
	}
	
	
}
