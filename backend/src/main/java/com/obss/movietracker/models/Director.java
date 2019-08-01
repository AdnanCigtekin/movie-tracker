package com.obss.movietracker.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "directors" )
public class Director {
/*
 * Director Properties: 
 * Name, Surname, BirthDate, Birth Place
 * 
 * */
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long directorId;
	
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private Date birthDate;
	
	@Column
	private String birthPlace;

	@ManyToMany(mappedBy="directors")
	private List<Movie> movies;

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	@Override
	public String toString() {
		return "[ name = " + name + " surname = " + surname + " birthDate = " + birthDate + " birthPlace = " + birthPlace + " ]";
	}
	
}
