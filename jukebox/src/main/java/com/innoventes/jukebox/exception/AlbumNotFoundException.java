package com.innoventes.jukebox.exception;

@SuppressWarnings("serial")
public class AlbumNotFoundException extends RuntimeException  {
	
	private String albumName;
	
	public AlbumNotFoundException(String albumName)
	{
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}
	
}
