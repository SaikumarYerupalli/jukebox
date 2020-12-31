package com.innoventes.jukebox.service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innoventes.jukebox.exception.MusicianNotFoundException;
import com.innoventes.jukebox.model.Album;
import com.innoventes.jukebox.repo.AlbumRepo;

@Service
public class AlbumService {

	private static Logger log = Logger.getLogger(AlbumService.class.getName());

	@Autowired
	private AlbumRepo albumRepo;
	
	public Album create(Album album) {

		Album albumEntityResponse = null;

		log.info(">> creating album ");

		albumEntityResponse = albumRepo.save(album);
		
		log.info("music album created with id " + albumEntityResponse.getAlbumId());

		return albumEntityResponse;
	}
	
	public List<Album> listAlbumsSortByDate(String musicianName)
	{
		
		List<Album> albumList = albumRepo.listAlbumByMusician(musicianName);
	
		if(albumList.isEmpty())
		{
			throw new MusicianNotFoundException(musicianName);
		}
		
		albumList.forEach(album->album.setMusicians(new LinkedList<>()));   // this is to avoid recursive output
		albumList.sort((Album album1,Album album2)->album1.getCreationDate().compareTo(album2.getCreationDate()));   // lambda expression for date comaparator
		
		
		return albumList;
	}

	public List<Album> listAlbumsSortByPrice(String musicianName) {
		
		List<Album> albumList = albumRepo.listAlbumByMusicianOrderByPrice(musicianName);
		
		if(albumList.isEmpty())
		{
			throw new MusicianNotFoundException(musicianName);
		}
		
		albumList.forEach(album->album.setMusicians(new LinkedList<>()));   // this is to avoid recursive output
		
		return albumList;	
	}

}
