<%@page session="false"%>
<html>
<form action="http://172.16.48.156:8080/twitter/post/twitter/" method="post">
	<h1>Bamba Code (Spring Social Twitter)</h1>	
	<h2>Tu usuario puede acceder a la app</h1>	
	<br><br>
	ACCESS TOKEN:
	<input type="text" name="accessToken" value='${accesToken}' style="width:550px">
	<br><br>
	ACCESS TOKEN SECRET:
	<input type="text" name="secretAccess" value='${secretAccess}' style="width:550px">
	<br><br>
	MENSAJE (max 140 caracteres): 
	<textarea name="mensaje" rows="1" cols="140">${mensaje}</textarea>
	<br><br>
	<input type="submit" value="Tuitear!">
</form>
</html>