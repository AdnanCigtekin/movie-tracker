package com.obss.movietracker.services;

import java.util.List;

import com.obss.movietracker.models.Movie;

public interface UserService {
	
	public List<Movie> getListOfMovies();
	
	public Boolean addToList(Long listId, Long movieId);
	
	public List<Movie> searchMoviesByName(String movieName);
	
}
