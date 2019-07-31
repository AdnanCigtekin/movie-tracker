package com.obss.movietracker.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "Movie" )
public class Movie {
/*
 * 
 * Movie Properties: NAme, Director, 
 * Release Date, IMDB Rating, Duration, Genre
 * 
 * */
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column( nullable = false )
	private String name;
	
	
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
	   name="MOV_DIR",
	   joinColumns=@JoinColumn(name="MOV_ID", referencedColumnName="ID"),
	   inverseJoinColumns=@JoinColumn(name="DIR_ID", referencedColumnName="ID"))
	//@Column( nullable = true )
	private List<Director> directors = new ArrayList<Director>();
	
	@Column( nullable = false )
	private Date releaseDate;
	
	@Column( nullable = false )
	private int duration;
	
	@Column( nullable = false )
	private String genre;

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	@Override
	public String toString() {
		if(directors.isEmpty())
			return "[ name = " + name + " releaseDate = " + releaseDate + " duration = " + duration + " genre = " + genre+ " ]";
		else
			return "[ name = " + name + " director = " + ((Director) directors.toArray()[0]).getName() + " " + ((Director) directors.toArray()[0]).getSurname() + " releaseDate = " + releaseDate + " duration = " + duration + " genre = " + genre+ " ]";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
