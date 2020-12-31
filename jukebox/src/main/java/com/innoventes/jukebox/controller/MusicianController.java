package com.innoventes.jukebox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.innoventes.jukebox.model.Musician;
import com.innoventes.jukebox.service.MusicianService;

@RestController
@RequestMapping("/v1/musician")
public class MusicianController {
	
	private static Logger log = Logger.getLogger(MusicianController.class.getName());

	@Autowired
	MusicianService musicianService;
	
	@PostMapping("/create")
	public Musician create(@Valid @RequestBody(required = true) Musician request) {
		log.info(">> rest api to create musician ");
		
		return musicianService.create(request);
	}
	
	@GetMapping("/list")
	public List<Musician> listMusicians(@RequestParam(name = "albumname") String albumName) {
		
		log.info(">> rest api to list musicians in order of names for album  "+albumName);
		
		return musicianService.listMusicianSortByName(albumName);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
