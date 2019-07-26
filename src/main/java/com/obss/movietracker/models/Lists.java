package com.obss.movietracker.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "userLists" )
public class Lists {

	@Id
	@Column(name = "LIST_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String listName;
	
	 @ManyToMany
	 @JoinTable(
	   name="LIST_MOV",
	   joinColumns=@JoinColumn(name="LIST_ID", referencedColumnName="LIST_ID"),
	   inverseJoinColumns=@JoinColumn(name="MOV_ID", referencedColumnName="ID"))
	private List<Movie> movies = new ArrayList<Movie>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private Users owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}
	
	
}
