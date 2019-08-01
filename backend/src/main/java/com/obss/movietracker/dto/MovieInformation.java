package com.obss.movietracker.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MovieInformation {

	private Long id;
	
	private String name;


	private List<String> directors = new ArrayList<String>();
	

	private Date releaseDate;
	

	private int duration;
	

	private String genre;


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


	public List<String> getDirectors() {
		return directors;
	}


	public void setDirectors(List<String> directors) {
		this.directors = directors;
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
