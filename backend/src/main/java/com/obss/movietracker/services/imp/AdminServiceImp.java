package com.obss.movietracker.services.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.DirectorDAO;
import com.obss.movietracker.dao.MovieListDAO;
import com.obss.movietracker.dao.RoleDAO;
import com.obss.movietracker.dao.MovieDAO;
import com.obss.movietracker.dao.UsersDAO;
import com.obss.movietracker.dto.MovieInformation;
import com.obss.movietracker.dto.UserCreateDTO;
import com.obss.movietracker.models.Director;
import com.obss.movietracker.models.Lists;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Role;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.AdminService;

@Service
public class AdminServiceImp  implements AdminService, UserDetailsService{

	@Autowired
	protected UsersDAO userRepository;

	@Autowired
	protected MovieDAO movieRepository;
	
	@Autowired
	protected DirectorDAO directorRepository;
	
	@Autowired
	private MovieListDAO movieListRepository;
	
	@Autowired
	private RoleDAO roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 List<Users> user = userRepository.findMyUserByUsername(username);
		 
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found by name: " + username);
		}
		return toUserDetails(user.get(0));
	}
	
    private UserDetails toUserDetails(Users userObject) {
    	
    	String a = userObject.getAuthorities().toArray()[0].toString();
    	
        return User.withUsername(userObject.getUsername())
                   .password(userObject.getPassword())
                   .roles(userObject.getAuthorities().toArray()[0].toString()).build();
    }
	
	@Override
	public Boolean addAdmin() {
		List<Users> admins = userRepository.findMyUserByUsername("admin");
		
		if(!admins.isEmpty())
			return false;
		
		Users admin = new Users();
		admin.setPassword(bcryptEncoder.encode("admin"));
		admin.setUsername("admin");
		admin.setEnabled(true);
		
		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepository.save(adminRole);
		
		Role endUserRole = new Role();
		endUserRole.setName("USER");
		roleRepository.save(endUserRole);
		
		List<Role> myLists = new ArrayList<Role>();
		myLists.add(adminRole);
		admin.setRoles(myLists);
		userRepository.save(admin);
		return true;
		
	}
	
	
	@Override
	public Boolean addUser(UserCreateDTO user) {
		try {
			Users newUser = new Users();
			
			Lists newWatchList = new Lists();
			newWatchList.setListName("watchlist");
			movieListRepository.save(newWatchList);
			
			Lists newFavoriteList = new Lists();
			newFavoriteList.setListName("favoritelist");
			movieListRepository.save(newFavoriteList);
		
			List<Lists> myLists = new ArrayList<Lists>();
			
			myLists.add(newWatchList);
			myLists.add(newFavoriteList);
			
			
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setEnabled(user.getEnabled());
			newUser.setMyLists(myLists);
			
			
			
			
//			for(Role s : newUser.getRoles()) {
//				Long temp =	Long.parseLong(s);
//				Role newDir = roleRepository.find;
//				newRoles.add(newDir);
//			}
			List<Role> newRoles = new ArrayList<Role>();
			newRoles.add(roleRepository.findById(user.getRoleId()).get());
			
			newUser.setRoles(newRoles);
			
			userRepository.save(newUser);
			
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
			Users user = userRepository.findById(id).get();
			user.setRoles(null);
			//Lists myList = movieListRepository.findById(user.getMyLists().get(0).getId()).get();
			if(!user.getMyLists().isEmpty()) {
				int i = 0;
				List<Lists> myLists = user.getMyLists();
				Long newId = user.getMyLists().get(i).getId();
				movieListRepository.deleteById(newId);
				movieListRepository.deleteById(newId + 1);
				user.setMyLists(null);

			}
			userRepository.save(user);
			
			userRepository.deleteById(id);	
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Boolean updateUser(UserCreateDTO user) {
		// TODO : Check for admin privileges
		try {
			Optional<Users> userList = userRepository.findById(user.getId());
			
			if(userList.empty() == null)
				return false;
			
			Users newUser = userList.get();

			if(user.getEnabled() != null)
				newUser.setEnabled(user.getEnabled());

			if(user.getPassword() != null)
				newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			if(user.getUsername() != null)
				newUser.setUsername(user.getUsername());
			if(user.getRoleId() != null) {
				List<Role> newRoles = new ArrayList<Role>();
				Optional<Role> inputRoleId = roleRepository.findById(user.getRoleId());
				newRoles.add(inputRoleId.get());
				newUser.setRoles(newRoles);
			}
			
			userRepository.save(newUser);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}

	@Override
	public List<Users> searchUser(String user) {
		List<Users> resultSet;
		if(!user.equals("admin"))
			resultSet = userRepository.findMyUsernameLike(user);
		else
			resultSet = userRepository.findMyUserByUsername(user);
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
