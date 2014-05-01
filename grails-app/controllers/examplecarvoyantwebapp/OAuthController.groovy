package examplecarvoyantwebapp

import grails.converters.JSON
import wslite.rest.RESTClient


class OAuthController {

	def grailsApplication
	def springSecurityService
	JSON vehilceInfo
	RESTClient carvoyantServer = new RESTClient("https://api.carvoyant.com")
	def basicAuth
	def redirectUri
	
	def vehicleInfo()
	{
		def response = carvoyantServer.get(path: '/v1/api/vehicle/', headers:["Authorization":"Bearer " + springSecurityService.currentUser.token])
		
		vehilceInfo =  response.json
		redirect(action: "example")
	}
	
	//presents the user with a login prompt (index.gsp)
    def index() 
	{
	}
	
	//redirects to Carvoyant login screen where user enters driver credentials
	def carvoyantLogin()
	{
		redirectUri = grailsApplication.config.carvoyant.appUrl + "/OAuth/example"
		redirect(url: "https://auth.carvoyant.com/OAuth/authorize?client_id="+ grailsApplication.config.carvoyant.clientId + "&redirect_uri=" + redirectUri + "&response_type=code")
	}
	
	//passes in the authorization code to retrieve token and refresh token
	private getToken(String code)
	{	
		basicAuth = (grailsApplication.config.carvoyant.key + ":" + grailsApplication.config.carvoyant.secret).toString().bytes.encodeBase64()
		def response = carvoyantServer.post(path: '/oauth/token', headers:["Authorization":"Basic " + basicAuth])
		{
			urlenc grant_type: 'authorization_code', code: code, redirect_uri: redirectUri		
		}
		
		springSecurityService.currentUser.token = response.json.access_token
		springSecurityService.currentUser.refreshToken = response.json.refresh_token
	}
	
	//passes iin the refresh token and receives new token and refresh token
	def refreshToken()
	{	
		def response = carvoyantServer.post(path: '/oauth/token', headers:["Authorization":"Basic " + basicAuth])
		{
			urlenc grant_type: 'refresh_token', refresh_token: springSecurityService.currentUser.refreshToken
		}

		springSecurityService.currentUser.token = response.json.access_token
		springSecurityService.currentUser.refreshToken = response.json.refresh_token
		
		redirect(action: "example")
	}
	
	//checks for code or token, then renders the main app screen (example.gsp)
	def example()
	{
		if(params.code)
		{
			getToken(params.code)
		}
		if(!springSecurityService.currentUser.token)
		{
			redirect(action: "index")
		}
		[token: springSecurityService.currentUser.token, vehicleInfo: vehilceInfo]
	}	
}
