import examplecarvoyantwebapp.User
import examplecarvoyantwebapp.UserRole
import examplecarvoyantwebapp.Role

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def testUser = User.findByUsername('testUser') ?: new User(username: 'testUser', password: 'password').save()
		UserRole.create testUser, adminRole, true

		
    }
    def destroy = {
    }
}
