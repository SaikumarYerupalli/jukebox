package com.innoventes.jukebox.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innoventes.jukebox.model.Album;
import com.innoventes.jukebox.service.AlbumService;

@RestController
@RequestMapping("/v1/album")
@Validated
public class AlbumController {
	
	private static Logger log = Logger.getLogger(AlbumController.class.getName());

	@Autowired
	AlbumService albumService;

	@PostMapping("/create")
	public Album createAlbum(@Valid @RequestBody(required = true) Album request) {
		
		log.info(" >> rest api to create album ");
		return albumService.create(request);
	}


	@GetMapping("/list")
	public List<Album> listAlbum(@RequestParam(name = "musician") String musicianName,
		@Valid	@Pattern(regexp = "date|price", flags = Pattern.Flag.CASE_INSENSITIVE) 
		@NotNull @NotBlank(message="orderby query param is mandatory") @RequestParam(name = "orderby") 
		 String orderBy) {
		
		log.info(">> rest api to list albums in order of date by musician "+musicianName);
		if(orderBy.equalsIgnoreCase("date"))
			return albumService.listAlbumsSortByDate(musicianName);
		else 
			return albumService.listAlbumsSortByPrice(musicianName);
		
	}
	
	
}
