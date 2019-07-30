package com.obss.movietracker.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.DirectorDAO;
import com.obss.movietracker.dao.MovieListDAO;
import com.obss.movietracker.dao.MovieDAO;
import com.obss.movietracker.dao.UsersDAO;
import com.obss.movietracker.dto.MovieInformation;
import com.obss.movietracker.models.Director;
import com.obss.movietracker.models.Lists;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.AdminService;

@Service
public class AdminServiceImp  implements AdminService{

	@Autowired
	protected UsersDAO userRepository;

	@Autowired
	protected MovieDAO movieRepository;
	
	@Autowired
	protected DirectorDAO directorRepository;
	
	@Autowired
	private MovieListDAO movieListRepository;
	
	@Override
	public Boolean addUser(Users user) {
		try {
			Lists newWatchList = new Lists();
			newWatchList.setListName("watchlist");
			movieListRepository.save(newWatchList);
			
			Lists newFavoriteList = new Lists();
			newFavoriteList.setListName("favoritelist");
			movieListRepository.save(newFavoriteList);
		
			List<Lists> myLists = new ArrayList<Lists>();
			
			myLists.add(newWatchList);
			myLists.add(newFavoriteList);
			
			user.setMyLists(myLists);
			
			Users newUser = userRepository.save(user);
			
			newWatchList.setOwner(newUser);
			newFavoriteList.setOwner(newUser);
			movieListRepository.save(newWatchList);
			movieListRepository.save(newFavoriteList);
			
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteUser(Long id) {
//		
//		try {
//			userRepository.delete(userRepository.findMyUserByUsername(username).get(0));
//			
//			return true;
//		}catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		
		try {
			userRepository.deleteById(id);		
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Boolean updateUser(Users user) {
		// TODO : try to get rid of whole user object
		try {
			Users newUser = userRepository.findById(user.getId()).get();

			if(user.getEnabled() != null)
				newUser.setEnabled(user.getEnabled());
			if(user.getIsAdmin() != null)
				newUser.setIsAdmin(user.getIsAdmin());
			if(user.getPassword() != null)
				newUser.setPassword(user.getPassword());
			if(user.getUsername() != null)
				newUser.setUsername(user.getUsername());
			
			userRepository.save(newUser);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}

	@Override
	public List<Users> searchUser(String user) {
		
		List<Users> resultSet = userRepository.findMyUsernameLike(user);
		//System.out.println(resultSet);
		
		
		//return userRepository.findAll().stream().filter((content) -> user == content.).findFirst().orElse(null);
		return resultSet;
	}

	@Override
	public Boolean addMovie(MovieInformation movie) {
		try {
			Movie newMovie = new Movie();
			
			List<Director> newDirectors = new ArrayList<Director>();
			for(String s : movie.getDirectors()) {
				Long temp =	Long.parseLong(s);
				Director newDir = directorRepository.findById(temp).get();
				newDirectors.add(newDir);
			}
			
			newMovie.setDirectors(newDirectors);
			newMovie.setReleaseDate(movie.getReleaseDate());
			newMovie.setDuration(movie.getDuration());
			newMovie.setGenre(movie.getGenre());
			newMovie.setName(movie.getName());
			
			movieRepository.save(newMovie);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteMovie(Long id) {
//		try {
//			movieRepository.delete(movieRepository.findMovieByName(id).get(0));
//			
//			return true;
//		}catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
		try {
			movieRepository.deleteById(id);		
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateMovie(MovieInformation movie) {
		try { 
			Movie newMovie = movieRepository.findById(movie.getId()).get();

			if(movie.getDirectors() != null) {
				//newMovie.setDirector(movie.getDirector());
				
				List<Director> newDirectors = new ArrayList<Director>();
				for(String s : movie.getDirectors()) {
					Long temp =	Long.parseLong(s);
					Director newDir = directorRepository.findById(temp).get();
					newDirectors.add(newDir);
				}
				newMovie.setDirectors(newDirectors);
			}
			newMovie.setDuration(movie.getDuration());
			newMovie.setGenre(movie.getGenre());
			newMovie.setName(movie.getName());
			newMovie.setReleaseDate(movie.getReleaseDate());
			
			
			movieRepository.save(newMovie);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Movie> searchMovie(String movie) {
		//TODO : Hocaya sor
		List<Movie> resultSet = movieRepository.findMyMovieLike(movie);
		
		return resultSet;
		
	}

	@Override
	public Boolean addDirector(Director director) {
		try {
			//director.setId(7L);
			
			directorRepository.save(director);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteDirector(Long id) {
//		try {
//			directorRepository.delete(directorRepository.findMovieByName(name).get(0));
//			
//			return true;
//		}catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
		try {
			directorRepository.deleteById(id);		
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Boolean updateDirector(Director director) {
		try {
			Director newDirector = directorRepository.findById(director.getDirectorId()).get();

			if(director.getName() != null)
				newDirector.setName(director.getName());
			if(director.getBirthDate() != null)
				newDirector.setBirthDate(director.getBirthDate());
			if(director.getBirthPlace() != null)
				newDirector.setBirthPlace(director.getBirthPlace());
			if(director.getSurname() != null)
				newDirector.setSurname(director.getSurname());
			
			directorRepository.save(newDirector);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Director> searchDirector(String director) {
		List<Director> resultSet = directorRepository.findDirectorLike(director);
		//System.out.println(resultSet);
		//return userRepository.findAll().stream().filter((content) -> user == content.).findFirst().orElse(null);
		return resultSet;
	}

	@Override
	public List<Movie> searchDirectorMovies(String director) {
		List<Director> resultSet = directorRepository.findDirectorByName(director);
		
		if(resultSet.isEmpty())
			return null;
		
		Long directorId = resultSet.get(0).getDirectorId();
		
		List<Movie> resultOfMovies = movieRepository.findMyDirectorMovie(directorId);
		return resultOfMovies;
	}

}
