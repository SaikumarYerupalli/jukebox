package com.innoventes.jukebox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innoventes.jukebox.model.Album;

@Repository
public interface AlbumRepo extends CrudRepository<Album, Long>{

	@Query("select a from Album a where a.name = :albumName ")
	Album findAlbumByName(@Param("albumName") String albumName);
	
	@Query("select a from Album a join a.musicians musician where musician.name = :musicianName ")
	List<Album> listAlbumByMusician(@Param("musicianName") String musicianName);
	
	@Query("select a from Album a join a.musicians musician where musician.name = :musicianName order by a.price ")
	List<Album> listAlbumByMusicianOrderByPrice(@Param("musicianName") String musicianName);

}
