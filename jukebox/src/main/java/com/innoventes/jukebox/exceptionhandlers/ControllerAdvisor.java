package com.innoventes.jukebox.exceptionhandlers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.innoventes.jukebox.exception.AlbumNotFoundException;
import com.innoventes.jukebox.exception.MusicianNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
		
	  	@ExceptionHandler(AlbumNotFoundException.class)
	    public ResponseEntity<Object> handleAlbumNotFoundException(
	    		AlbumNotFoundException ex, WebRequest request) {

	        return new ResponseEntity<>(ex.getAlbumName()+" album not found", HttpStatus.NOT_FOUND);
	    }
	  	
		@ExceptionHandler(MusicianNotFoundException.class)
	    public ResponseEntity<Object> handleMusicianNotFoundException(
	    		MusicianNotFoundException ex, WebRequest request) {

	        return new ResponseEntity<>(ex.getMusicianName()+" musician not found", HttpStatus.NOT_FOUND);
	    }
	
		@ExceptionHandler({ TransactionSystemException.class })   
		public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {
			
		    Throwable cause = ((TransactionSystemException) ex).getRootCause();
		    if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
		        
		        List<String> violationMessages = constraintViolations.stream().map(message->message.getMessage()).collect(Collectors.toList());
		        
		        return new ResponseEntity<>(Collections.singletonMap("vioaltions",violationMessages), HttpStatus.BAD_REQUEST);
		    }
		    
		    return new ResponseEntity<>(" Unknown error ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler({ org.hibernate.exception.ConstraintViolationException.class })   
		public ResponseEntity<Object> handleHibernateConstraintViolation(org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {		    
		    return new ResponseEntity<>(" Item with same name already exists ", HttpStatus.BAD_REQUEST);
		}
		
		
	    @ExceptionHandler
	    @ResponseBody
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String,Object> handle(ConstraintViolationException exception) {
	        return error(exception.getConstraintViolations()
	                .stream()
	                .map(ConstraintViolation::getMessage)
	                .collect(Collectors.toList()));
	    }

	    private Map<String,Object> error(Object message) {
	        return Collections.singletonMap("vioaltions", message);
	    }
		
		
}
