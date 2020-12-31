package com.innoventes.jukebox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innoventes.jukebox.model.Musician;

@Repository
public interface MusicianRepo extends CrudRepository<Musician, Long> {

	@Query("select me from Musician me where me.name = :musicianName ")
	Musician findMusicianByName(@Param("musicianName") String musicianName);
	
	@Query("select me from Musician me join me.albums album where album.name = :albumName order by me.name ")
	List<Musician> listMusiciansByAlbumOrderByName(@Param("albumName") String albumName);

}
