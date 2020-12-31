package com.innoventes.jukebox.exception;

@SuppressWarnings("serial")
public class MusicianNotFoundException extends RuntimeException {
	
	private String musicianName;
	
	public MusicianNotFoundException(String musicianName)
	{
		this.musicianName = musicianName;
	}

	public String getMusicianName() {
		return musicianName;
	}

}
