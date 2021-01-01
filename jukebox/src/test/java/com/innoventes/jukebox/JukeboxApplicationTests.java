/*package com.innoventes.jukebox;

import java.net.URI;
import java.net.URISyntaxException;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class JukeboxApplicationTests {
	
	@LocalServerPort
	int randomServerPort;

	@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException 
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/employees";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    
	    
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("employeeList"));
	}
	
	@Test
	void contextLoads() {
	}

}
*/