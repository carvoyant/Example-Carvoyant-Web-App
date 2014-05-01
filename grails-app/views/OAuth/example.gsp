<!DOCTYPE html>
<html>
<head>
    <title>Example Carvoyant Web App</title>
</head>
<body>
	<g:form>
			<p>Click the Refresh Token button to submit your refresh token and receive a new token.</p>
	    	Token:
	    	<br>
	    	<g:textArea name="token" value="${token}" cols="60" rows="1"></g:textArea>
	    	<br>
	    	<g:actionSubmit  action="refreshToken" value="Refresh Token" style="width: 100px; vertical-align: top"/>
	    	<br>
	    	<br>
	    	<br>
	    	<br>
	    	<br>
	    	<p>Click the Vehicle Info button to use your token to call the Carvoyant API and retrieve the driver's vehicle data.</p>
	    	Vehicle Data:
	    	<br>
	    	<g:textArea name="vehicleInfo" value="${vehicleInfo}" cols="60" rows="22"></g:textArea>
	    	<br>
	    	<g:actionSubmit action="vehicleInfo" value="Vehicle Info"  style="width: 100px; vertical-align: top"/>
	    	
	</g:form>
</body>
</html>