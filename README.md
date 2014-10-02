
![alt tag](https://github.com/elbambaproject/spring-social-twitter-OAuth/blob/master/src/main/webapp/WEB-INF/github-img/header.png)
### Spring Social Twitter OAuth  
##<b>@author ptamburro</b>

Ejemplo de Spring Social con Twitter, que incluye la autentificacion OAuth 1.0a.<br>
Esta aplicacion permite desde una jsp generar tuits directamente en la cuenta de usuarios
que den los permisos correspondientes. 

 

### Configuraciones previas

1. Crear la app en Twitter
   (buen tutorial para hacerlo: <i>http://iag.me/socialmedia/how-to-create-a-twitter-app-in-8-easy-steps/</i>)<br> 
   Tener en cuenta de agregarle permisos de 'Read and Write' y url callback debe ser <i>'http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter'</i> 
   (con ip de la maquina donde se esta levantando tomcat)
   
2. Actualizar el archivo de configuracion 'twitter.properties'. Del paso anterior, tomar el valor de apiKey/secretKey y actualizarlas en el archivo, 
   tambien callbackUrl debe ser <i>'http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter'</i> (con ip de la maquina donde se esta levantando tomcat)  

3. Levantar en Tomcat puerto 8080 (modificando el path para que quede en <i>'/twitter'</i>)  
   

### Prueba del codigo.

1.  Acceder a http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter
         
    Esto lo que hace es pedir un token a twitter y mostrar la pantalla de autorizacion de twitter para acceder a la app. (oauth)
    Luego dar la autorizacion, se obtienen los access token del usuario (oauth) 
    se redirige a la pantalla que indica el servicio /callback 
    
2.  En una pantalla sencilla se muestran los access token de la cuenta que acepto la aplicacion y permite postear mensajes, en la cuenta
    del usuario, utilizando servicios del codigo.     


### Servicios expuestos

	http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter  (obtiene de twitter request token / OAuth)
	http://xxx.xx.xx.xxx:8080/twitter/callback/twitter (Twitter direcciona a esta url luego de que el usuario acepte permisos de app)
	http://xxx.xx.xx.xxx:8080/twitter/post/twitter (postea en cuentas de twitter luego de tener tokens de acceso)


### Tecnologias usadas
- Spring Social Twitter
- Spring MVC
- Scribe OAuth Library
- Maven
