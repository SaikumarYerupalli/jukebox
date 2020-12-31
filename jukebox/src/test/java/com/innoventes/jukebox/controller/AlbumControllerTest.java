package com.innoventes.jukebox.controller;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.innoventes.jukebox.model.Album;
import com.innoventes.jukebox.model.Musician;
import com.innoventes.jukebox.service.AlbumService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlbumControllerTest {

	@LocalServerPort
	int randomServerPort;

	@MockBean
	private AlbumService albumService;

	@Test
	public void testCreateAlbum() {
		Album testAlbum = new Album();

		testAlbum.setName("Starboys");
		testAlbum.setDescription("Star Boys Album");
		testAlbum.setCreationDate(new Date());
		testAlbum.setPrice(900);
		testAlbum.setType("Melody");

		Musician musician = new Musician();
		musician.setName("Weeknd");
		musician.setMusicType("Instumentalist");

		testAlbum.setMusicians(Arrays.asList(musician));

		Mockito.when(albumService.create(Mockito.any(Album.class))).thenReturn(testAlbum);

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/album/create";

		ResponseEntity<Album> successResult = restTemplate.postForEntity(baseUrl, testAlbum, Album.class);

		// Verify request succeed
		Assert.assertEquals(200, successResult.getStatusCodeValue()); // success
																// message
		
		testAlbum.setName("st");   //this is for name length vioaltion 
		
		ResponseEntity<Album> badRequestResult = restTemplate.postForEntity(baseUrl, testAlbum, Album.class);

		Assert.assertEquals(400, badRequestResult.getStatusCodeValue());

	}
	
	
	@Test
	public void tesListAlbum() {
		Album testAlbum = new Album();

		testAlbum.setName("Starboys");
		testAlbum.setDescription("Star Boys Album");
		testAlbum.setCreationDate(new Date());
		testAlbum.setPrice(900);
		testAlbum.setType("Melody");

		Musician musician = new Musician();
		musician.setName("Weeknd");
		musician.setMusicType("Instumentalist");

		testAlbum.setMusicians(Arrays.asList(musician));

		Mockito.when(albumService.listAlbumsSortByDate("Weekend")).thenReturn(Arrays.asList(testAlbum));

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/album/list";
		
		ResponseEntity<String> successResponse = restTemplate.getForEntity(baseUrl+"?name=\"Weekend\"&orderby=date", String.class);
		
		Assert.assertEquals(200, successResponse.getStatusCodeValue()); // success message
		
		ResponseEntity<String> badRequestResponse = restTemplate.getForEntity(baseUrl, String.class);
		
		Assert.assertEquals(400, badRequestResponse.getStatusCodeValue()); //  bad request message
		
		testAlbum.setName("st");   //this is for name length vioaltion 
		
		ResponseEntity<Album> badRequestResult = restTemplate.postForEntity(baseUrl, testAlbum, Album.class);

		Assert.assertEquals(400, badRequestResult.getStatusCodeValue());

	}
}
