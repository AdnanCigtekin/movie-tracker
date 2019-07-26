package com.obss.movietracker.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "users" )
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@Column ( nullable = false )
	private String username;
	
	@Column( nullable = false)
	private String password;
	
	@Column( nullable = false )
	private Boolean enabled;
	
	@Column( nullable = false )
	private Boolean isAdmin;
	
	@OneToMany( mappedBy="owner", targetEntity=Lists.class)
	private List<Lists> myLists;
	
	
	public List<Lists> getMyLists() {
		return myLists;
	}

	public void setMyLists(List<Lists> myLists) {
		this.myLists = myLists;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "[ username = " + username + " password = " + password + " enabled = " + enabled + " isAdmin = " + isAdmin + " ]";
	}
	
}
