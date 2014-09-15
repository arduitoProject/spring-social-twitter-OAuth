
>>>>>> Spring Social Twitter OAuth
>>>>>> @author ptamburro

Ejemplo de Spring Social con Twitter, que incluye la autentificacion OAuth 1.0a.<br>
Esta aplicacion permite desde una jsp generar tuits directamente en la cuenta de usuarios
que den los permisos correspondientes. 


>>>>> Flujo de la app

  1. Solicita Token 



 

>>>>> Configuraciones previas

1. Crear la app en Twitter
   (buen tutorial para hacerlo ->  http://iag.me/socialmedia/how-to-create-a-twitter-app-in-8-easy-steps/ ) 
   Tener en cuenta de agregarle permisos de 'Read and Write'
   








Implementacion.

1 ) levantar en Tomcat puerto 8080 (modificando el path para que quede en '/twitter')
