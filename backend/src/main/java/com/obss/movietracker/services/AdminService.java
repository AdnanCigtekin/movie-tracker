package com.obss.movietracker.services;

import java.util.List;

import com.obss.movietracker.dto.MovieInformation;
import com.obss.movietracker.dto.UserCreateDTO;
import com.obss.movietracker.models.Director;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Users;

public interface AdminService {

	public Boolean addAdmin();
	
	/*		USER OPERATIONS			*/
	public Boolean addUser(UserCreateDTO user);
	
	public Boolean deleteUser(Long id);
	
	Boolean updateUser(UserCreateDTO user);
	
	public List<Users> searchUser(String user);
	

	
	/*		MOVIE OPERATIONS		*/
	public Boolean addMovie(MovieInformation movie);
	
	public Boolean deleteMovie(Long id);
	
	public Boolean updateMovie(MovieInformation movie);
	
	public List<Movie> searchMovie(String movie);
	
	public List<Movie> getAllMovies();
	
	/*		DIRECTOR OPERATIONS		*/
	public Boolean addDirector(Director director);
	
	public Boolean deleteDirector(Long id);
	
	public Boolean updateDirector(Director director);
	
	public List<Director> searchDirector(String director);
	
	public List<Movie> searchDirectorMovies(String director);

	public List<Director> getAllDirectors();
	
	
	
}
