#Example Carvoyant Web Application
Provides an example of Carvoyant API integration and [OAuth2](http://tools.ietf.org/html/rfc6749) implementation into a simple Grails web application.

##Configuration
To run this application the grails environment must be set up with the appropriate plugins and configured properly

###BuildConfig.groovy
Both the [Spring Security](http://grails.org/plugin/spring-security-core) and [wslite](https://github.com/jwagenleitner/groovy-wslite) plugins are added to this file's "plugin" closure and are therefore installed by grails when dependencies are refreshed.

###S2-quickstart
From a grails prompt, the [s2-quickstart](http://grails-plugins.github.io/grails-spring-security-core/docs/manual/ref/Scripts/s2-quickstart.html) script must be run to setup Spring Security.  In this application, the values "User" and "Role" were used as parameters. When ran, the script created the Domain Classes User, Role, and UserRole. The s2-quickstart script also adds values to the Config.groovy file.

###Config.groovy
Spring Security configuration settings are added to this file by the s2-quickstart script.  The static rules value was altered so that all "/OAuth" are password protected. This file contains a few values that will be unique to each Carvoyant application developer: 

+ Your Carvoyant enabled application's client ID
+ Your Carvoyant API key
+ Your Carvoyant API Secret
+ The URL your application will be hosted at


###BootStrap.groovy
An example User is created that is used to log into the application via Spring Security.


##Other Files of interest

###User.groovy
This file was created by the Spring Security s2-quickstart script.  For this application the User class has had both "token" and "refreshToken" values added.

###OAuthController
This is where the main logic flow of the application is contained.  It has the methods responsible for communicating with the Carvoyant API and OAuth servers.  

###Views
Two gsp files were made to supply a simple UI:

+ index.gsp
+ example.gsp