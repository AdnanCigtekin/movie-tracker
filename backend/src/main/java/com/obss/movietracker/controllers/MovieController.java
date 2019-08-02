package com.obss.movietracker.controllers;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.dto.IdContainer;
import com.obss.movietracker.dto.MovieInformation;
import com.obss.movietracker.dto.MovieListInformation;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.imp.AdminServiceImp;
import com.obss.movietracker.services.imp.UserServiceImp;
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/movie")
public class MovieController {
	
    @Autowired
    @Qualifier("adminService")
    private AdminServiceImp adminService;
	
    @Autowired
    @Qualifier("userService")
    private UserServiceImp userService;
	
    /*		MOVIE		*/
	@PostMapping("/")
	@Secured({"ROLE_ADMIN"})
	//@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMovie(@RequestBody MovieInformation movie) {
    	
    	Boolean isSuccess = adminService.addMovie(movie);		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @PutMapping("/")
    @Secured({"ROLE_ADMIN"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateMovie(@RequestBody MovieInformation movie) {
    	
    	Boolean isSuccess = adminService.updateMovie(movie);		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    } 
    
    @DeleteMapping("/")
    @Secured({"ROLE_ADMIN"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@RequestBody IdContainer myId) {
    	
    	Boolean isSuccess = adminService.deleteMovie(myId.getId());		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    } 
     
    @GetMapping("/search")
    @Secured({"ROLE_ADMIN"})
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> searchMovie(@RequestParam(value="name") String movie) {
    	
    	List<Movie> movieList = adminService.searchMovie(movie);		
    	
    	//Returning the output
    	StringBuffer output = new StringBuffer();
    	output.append("{ \n		'output' : [\n ");
    	for(Movie u : movieList) {
    		output.append("\t\t\t\t\t" + u.toString() + "\n");
    	}
    	output.append("\t\t\t\t\t]\n}");
    	
    	return new ResponseEntity<>(output.toString(), HttpStatus.ACCEPTED);
    	
    }   
	
   
	
    @GetMapping("/list")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
   // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getListOfMovies() {
    	
    	List<Movie> movieList = userService.getListOfMovies();
		
    	
    	if(movieList != null) {
    		if(!movieList.isEmpty())
    			return new ResponseEntity<>(movieList.toString(), HttpStatus.ACCEPTED);
    		else
    			return new ResponseEntity<>("EMPTY", HttpStatus.ACCEPTED);
    	}
    	else
    		return new ResponseEntity<>("Failed to fetch", HttpStatus.BAD_REQUEST);

    } 
    
    @PostMapping("/list")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
   // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addMovieToList(@RequestBody MovieListInformation info) {
    	
    	Boolean isSuccess = userService.addToList(info.getListId(), info.getMovieId());
		
    	
    	if(isSuccess) {
    			return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	}
    	else
    		return new ResponseEntity<>("Failed to add", HttpStatus.BAD_REQUEST);

    } 

    @GetMapping("/list/search")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getListOfSearchedMovies(@RequestParam String name) {
    	
    	List<Movie> movieList = userService.searchMoviesByName(name);
		
    	
    	if(movieList != null) {
    		if(!movieList.isEmpty())
    			return new ResponseEntity<>(movieList.toString(), HttpStatus.ACCEPTED);
    		else
    			return new ResponseEntity<>("EMPTY", HttpStatus.ACCEPTED);
    	}
    	else
    		return new ResponseEntity<>("Failed to fetch", HttpStatus.BAD_REQUEST);

    } 
    
    @GetMapping("/search-all")
    @Secured({"ROLE_ADMIN"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getEveryMovie() {
    	
    	JSONObject jos = new JSONObject();
    	List<Movie> movieList = adminService.getAllMovies();		
    	JSONArray joArray = new JSONArray();

    	Integer i = 0;
    	for(Movie u : movieList) {
    		JSONObject newObj = new JSONObject();
//    		newObj.put(i.toString(), u.);
    		newObj.put("duration", u.getDuration());
    		newObj.put("directors", u.getDirectors());
    		newObj.put("genre", u.getGenre());
    		newObj.put("id", u.getId());
    		newObj.put("name", u.getName());
    		newObj.put("releaseDate", u.getReleaseDate());
    		joArray.put(newObj);
    		
    	}
    	jos.put("datas", joArray);
    	//output.append("\t\t\t\t\t]\n}");
    	String output = jos.toString();
    	return new ResponseEntity<>(output, HttpStatus.OK);
    	
    }

}
