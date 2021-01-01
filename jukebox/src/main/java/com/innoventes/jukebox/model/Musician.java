package com.innoventes.jukebox.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="MUSICIAN")
@Access(value=AccessType.FIELD)
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "musicianId")   // this is to solve infinte recursion 
public class Musician {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "musicianIdSequence")
	@SequenceGenerator(name = "musicianIdSequence", sequenceName = "MUSICIAN_ID_SEQUENCE",allocationSize=1)
	@Column(name = "MUSICIAN_ID")
	private Long musicianId;
	
	@Column(name = "MUSICIAN_NAME",unique=true)
	@Length(min=3)
	@NotNull
	@NotBlank(message="Name is mandatory")
	private String name;
	
	@Column(name = "MUSICIAN_TYPE")
	@Pattern(regexp = "Vocalist|Instrumentalist", flags = Pattern.Flag.CASE_INSENSITIVE)
	@NotBlank(message="Music Type  is mandatory")
	private String musicType;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="musicians")*/
	
	@ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="album_musicians",joinColumns= { @JoinColumn(name = "MUSICIAN_ID")},inverseJoinColumns = {@JoinColumn(name="album_id")})
    private List<Album> albums;	
	
	
	
	public Musician() {
		super();
	}

	public Musician(String name, String musicType,List<Album> albums) {
		super();
		this.name = name;
		this.musicType = musicType;
		this.albums=albums;
	}

	public Long getMusicianId() {
		return musicianId;
	}

	public void setMusicianId(Long musicianId) {
		this.musicianId = musicianId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(this == object)
			return true;
		
		if(object == null || object.getClass()!=this.getClass())
			return false;
		
		Musician musician = (Musician)object;
		
		return (musician.getMusicianId() == this.getMusicianId());
		
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	@Override
	public int hashCode(){
		return Integer.parseInt(this.getMusicianId().toString());
	}
}
	
