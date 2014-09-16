
>> Spring Social Twitter OAuth <<
<b>@author ptamburro</b>

Ejemplo de Spring Social con Twitter, que incluye la autentificacion OAuth 1.0a.<br>
Esta aplicacion permite desde una jsp generar tuits directamente en la cuenta de usuarios
que den los permisos correspondientes. 

 

>> Configuraciones previas <<

1. Crear la app en Twitter
   (buen tutorial para hacerlo ->  http://iag.me/socialmedia/how-to-create-a-twitter-app-in-8-easy-steps/) 
   Tener en cuenta de agregarle permisos de 'Read and Write' y url callback debe ser <i>'http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter'</i> 
   (con ip de la maquina donde se esta levantando tomcat)
   
2. Actualizar el archivo de configuracion 'twitter.properties'. Del paso anterior, tomar el valor de apiKey/secretKey y actualizarlas en el archivo, 
   tambien callbackUrl debe ser <i>'http://xxx.xx.xx.xxx:8080/twitter/oauth/twitter'</i> (con ip de la maquina donde se esta levantando tomcat)  

3. Al agregar el proyecto a Tomcat, asegurarse que el path del mismo sea <i>/twitter</i>    
   








Implementacion.

1 ) levantar en Tomcat puerto 8080 (modificando el path para que quede en '/twitter')