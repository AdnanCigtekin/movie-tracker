package com.obss.movietracker.services;

import java.util.List;

import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Users;

public interface UserService {
	
	public List<Movie> getListOfMovies();
	
	public Boolean addToList(Long listId, Long movieId);
	
	public List<Movie> searchMoviesByName(String movieName);
	
	public String getUserRole(String username);
	
	public List<Users> getAllUsers();
	
}
