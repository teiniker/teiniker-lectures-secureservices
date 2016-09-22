How add a new user to the Wildfly configuration?
-------------------------------------------------------------------------------

	cd JBOSS_HOME/bin
	$ ./add-user.sh
	- b (Application user)
	- username: teacher
	- password: teacher
	- roles: admin,user
	- new user used for AS to AS communication: no 		

	=> JBOSS_HOME/standalone/configuration/application-users.properties
		teacher=de1d9228df96c2d1049a8381797e5da0
	
	=> JBOSS_HOME/standalone/configuration/application-roles.properties
		teacher=admin,user


How to use the resources from SoapUI?
-------------------------------------------------------------------------------

	URL: http://localhost:8080/REST-EJB-Authorization/v1/users
	URL: http://localhost:8080/REST-EJB-Authorization/v1/products
		
	Authorization : Basic	
	Username: student
	Password: student
	Authenticate pre-emptively
	
	
REST Application Configuration: web.xml
-------------------------------------------------------------------------------

	<security-role>
		<role-name>user</role-name>
	</security-role>

	<security-role>
		<role-name>admin</role-name>
	</security-role>
	
	<security-constraint>
		<web-resource-collection>
			<web-resource-name>user resources</web-resource-name>
			<url-pattern>/v1/products</url-pattern>
		</web-resource-collection>
		<!-- turn off TLS (for testing only) 
		<user-data-constraint> 
			<transport-guarantee>CONFIDENTIAL</transport-guarantee> 
		</user-data-constraint> 
		-->
		<auth-constraint>
			<role-name>user</role-name>
		</auth-constraint>
	</security-constraint>

	<security-constraint>
		<web-resource-collection>
			<web-resource-name>admin resources</web-resource-name>
			<url-pattern>/v1/users</url-pattern>
		</web-resource-collection>
		<!-- turn off TLS (for testing only) 
		<user-data-constraint> 
			<transport-guarantee>CONFIDENTIAL</transport-guarantee> 
		</user-data-constraint> 
		-->
		<auth-constraint>
			<role-name>admin</role-name>
		</auth-constraint>
	</security-constraint>

	<login-config>
		<auth-method>BASIC</auth-method>
		<realm-name>ApplicationRealm</realm-name>
	</login-config>
		

How to test client permissions?
-------------------------------------------------------------------------------
Using SoapUI we can simulate different clients:
	
	user: http://localhost:8080/REST-EJB-Authorization/v1/products
		student	=> HTTP/1.1 200 OK
		teacher	=> HTTP/1.1 200 OK
		
	admin: http://localhost:8080/REST-EJB-Authorization/v1/users
		student => HTTP/1.1 403 Forbidden
		teacher => HTTP/1.1 200 OK
