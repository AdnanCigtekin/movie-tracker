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
import com.obss.movietracker.models.Director;
import com.obss.movietracker.models.Movie;
import com.obss.movietracker.services.imp.AdminServiceImp;
import static com.obss.movietracker.configurations.SecurityConstants.REACT_ORIGIN;

@CrossOrigin(origins = REACT_ORIGIN, maxAge=3600)
@RestController
@RequestMapping("/director")
public class DirectorController {
	
    @Autowired
    @Qualifier("adminService")
    private AdminServiceImp adminService;
	
	 /*		DIRECTOR		*/
		@PostMapping("/")
		@Secured({"ROLE_ADMIN"})
		//@PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<String> addDirector(@RequestBody Director director) {
	    	
	    	Boolean isSuccess = adminService.addDirector(director);		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    }
	    
	    @PutMapping("/")
	    @Secured({"ROLE_ADMIN"})
	    //@PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> updateMovie(@RequestBody Director director) {
	    	
	    	Boolean isSuccess = adminService.updateDirector(director);		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    } 
	    
	    @DeleteMapping("/")
	    @Secured({"ROLE_ADMIN"})
	    //@PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> deleteDirector(@RequestBody IdContainer myId) {
	    	
	    	Boolean isSuccess = adminService.deleteDirector(myId.getId());		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    } 
	     
	    @GetMapping("/search")
	    @Secured({"ROLE_ADMIN"})
	    //@PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> searchDirector(@RequestParam(value="name") String director) {
	    	
	    	List<Director> directorList = adminService.searchDirector(director);		
	    	
	    	//Returning the output
	    	StringBuffer output = new StringBuffer();
	    	output.append("{ \n		'output' : [\n ");
	    	for(Director u : directorList) {
	    		output.append("\t\t\t\t\t" + u.toString() + "\n");
	    	}
	    	output.append("\t\t\t\t\t]\n}");
	    	
	    	return new ResponseEntity<>(output.toString(), HttpStatus.ACCEPTED);
	    	
	    }   
	    
	    
	    @GetMapping("/")
	    @Secured({"ROLE_ADMIN"})
	    //@PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> searchDirectorMovies(@RequestParam(value="name") String director) {
	    	
	    	List<Movie> directorList = adminService.searchDirectorMovies(director);		
	    	
	    	//Returning the output
	    	StringBuffer output = new StringBuffer();
	    	output.append("{ \n		'output' : [\n ");
	    	for(Movie u : directorList) {
	    		output.append("\t\t\t\t\t" + u.toString() + "\n");
	    	}
	    	output.append("\t\t\t\t\t]\n}");
	    	
	    	return new ResponseEntity<>(output.toString(), HttpStatus.ACCEPTED);
	    	
	    }

	    @GetMapping("/search-all")
	    @Secured({"ROLE_ADMIN"})
	  //  @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> getEveryDirector() {
	    	
	    	JSONObject jos = new JSONObject();
	    	List<Director> directorList = adminService.getAllDirectors();		
	    	JSONArray joArray = new JSONArray();

	    	Integer i = 0;
	    	for(Director u : directorList) {
	    		JSONObject newObj = new JSONObject();
//	    		newObj.put(i.toString(), u.);
	    		newObj.put("surname", u.getSurname());
	    		newObj.put("birthDate", u.getBirthDate());
	    		newObj.put("birthPlace", u.getBirthPlace());
	    		newObj.put("id", u.getDirectorId());
	    		newObj.put("name", u.getName());
	    		joArray.put(newObj);
	    		
	    	}
	    	jos.put("datas", joArray);
	    	//output.append("\t\t\t\t\t]\n}");
	    	String output = jos.toString();
	    	return new ResponseEntity<>(output, HttpStatus.OK);
	    	
	    }

}
