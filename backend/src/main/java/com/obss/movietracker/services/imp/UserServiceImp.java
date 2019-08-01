package com.obss.movietracker.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.obss.movietracker.dao.MovieListDAO;
import com.obss.movietracker.dao.MovieDAO;
import com.obss.movietracker.dao.UsersDAO;
import com.obss.movietracker.models.Lists;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.UserService;

public class UserServiceImp implements UserService{

	@Autowired
	protected UsersDAO userRepository;
	
	@Autowired
	protected MovieDAO movieRepository;
	
	@Autowired
	protected MovieListDAO movieListRepository;
	
	@Override
	public List<Movie> getListOfMovies() {
		
		return movieRepository.findAll();
	}

	@Override
	public Boolean addToList(Long listId, Long movieId) {
		try {
			Lists myLists = movieListRepository.findById(listId).get();
			
			Movie myMovie = movieRepository.findById(movieId).get();
			
			List<Movie> myMovies = myLists.getMovies();
			
			myMovies.add(myMovie);
			
			myLists.setMovies(myMovies);
			
			movieListRepository.save(myLists);
			
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public List<Movie> searchMoviesByName(String movieName) {
		
		return movieRepository.findMovieByName(movieName);
	}

	
	
	@Override
	public String getUserRole(String username) {
		List<Users> newUser = userRepository.findMyUserByUsername(username);
		if(newUser.isEmpty())
			return "";
		System.out.println("RETURNED SUCCESFULLY");
		return newUser.get(0).getRoles().get(0).getName();
		
	}

	@Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

}
