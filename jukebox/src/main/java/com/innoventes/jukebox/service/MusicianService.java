package com.innoventes.jukebox.service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innoventes.jukebox.exception.AlbumNotFoundException;
import com.innoventes.jukebox.model.Musician;
import com.innoventes.jukebox.repo.MusicianRepo;

@Service
public class MusicianService {

	private static Logger log = Logger.getLogger(MusicianService.class.getName());

	@Autowired
	private MusicianRepo musicianRepo;

	public Musician create(Musician musician) {
		Musician musicianEntityResponse = null;

		log.info(">> creating musician ");
		
		musicianEntityResponse = musicianRepo.save(musician);
		log.info("Musician created with id " + musicianEntityResponse.getMusicianId());	
		return musicianEntityResponse;
	}
	
	public List<Musician> listMusicianSortByName(String albumName) {
		
		
		List<Musician> musicianList = musicianRepo.listMusiciansByAlbumOrderByName(albumName);
		
		if(musicianList.isEmpty())
			throw new AlbumNotFoundException(albumName);
		
		musicianList.forEach(musician->musician.setAlbums(new LinkedList<>()));
		
		return musicianList;
	}
}
