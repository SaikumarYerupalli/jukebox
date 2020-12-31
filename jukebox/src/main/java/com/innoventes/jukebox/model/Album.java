package com.innoventes.jukebox.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="MUSIC_ALBUM")
@Access(value=AccessType.FIELD)
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "albumId")  // this is to solve infinte recursion 
public class Album {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "albumIdSequence")
	@SequenceGenerator(name = "albumIdSequence", sequenceName = "ALBUM_ID_SEQUENCE",allocationSize=1)
	@Column(name = "ALBUM_ID")
	private Long albumId;
	
	@Column(name = "NAME",unique=true)
	@Length(min=5)
	@NotNull
	@NotBlank(message="Name is mandatory")
	private String name;

	@Column(name = "MUSIC_TYPE")
	private String type;
	
	@Column(name = "PRICE")
	@Min(100)
	@Max(1000)
	@NotNull
	private int price;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATION_DATE")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	private Date creationDate;
	
	@ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="album_musicians",joinColumns= { @JoinColumn(name = "album_id")},inverseJoinColumns = {@JoinColumn(name="musician_id")})
	@NotNull
    private List<Musician> musicians;
	
	
	
	public Album() {
		super();
	}

	public Album(String name, String type, int price, String description, List<Musician> musicians) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
		this.musicians = musicians;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	}

	
	@Override
	public boolean equals(Object object)
	{
		if(this == object)
			return true;
		
		if(object == null || object.getClass()!=this.getClass())
			return false;
		
		Album album = (Album)object;
		
		return (album.getAlbumId() == this.getAlbumId());
		
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	@Override
	public int hashCode(){
		return Integer.parseInt(this.getAlbumId().toString());
	}
	
}
