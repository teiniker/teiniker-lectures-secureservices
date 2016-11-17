package org.se.lab.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.se.lab.UserDTO;

public class UserServiceXMLTest 
	extends AbstractTestBase
{
	private static final JdbcTestHelper JDBC_HELPER = new JdbcTestHelper();
	
	@Before
	public void init()
	{
		JDBC_HELPER.executeSqlScript("sql/createUserTable.sql");
		JDBC_HELPER.executeSqlScript("sql/insertUserTable.sql");
	}
	
	@After
	public void destroy()
	{
		JDBC_HELPER.executeSqlScript("sql/dropUserTable.sql");		
	}
	
	
	@Test
	public void testFindAll() throws IOException, JAXBException
	{
		Client client = ClientBuilder.newClient();
		List<UserDTO> response  = client	// don't hardcode URLs !!!
					.target("http://localhost:8080").path("REST-EJB-UserService/v1/users")
					.request().accept("application/xml").get(new GenericType<List<UserDTO>>() {});

		System.out.println(response);
	}


	@Test
	public void testFindById() throws IOException, JAXBException
	{
		Client client = ClientBuilder.newClient();
		UserDTO response = client
					.target("http://localhost:8080").path("REST-EJB-UserService/v1/users/1")
					.request().accept("application/xml").get(UserDTO.class);

		System.out.println(response);
	}

}



