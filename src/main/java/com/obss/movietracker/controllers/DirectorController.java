package com.obss.movietracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.obss.movietracker.services.imp.AdminServiceImp;

@RestController
@RequestMapping("/director")
public class DirectorController {
	
    @Autowired
    @Qualifier("adminService")
    private AdminServiceImp adminService;
	
	 /*		DIRECTOR		*/
		@PostMapping("/")
	    public ResponseEntity<String> addDirector(@RequestBody Director director) {
	    	
	    	Boolean isSuccess = adminService.addDirector(director);		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    }
	    
	    @PutMapping("/")
	    public ResponseEntity<String> updateMovie(@RequestBody Director director) {
	    	
	    	Boolean isSuccess = adminService.updateDirector(director);		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    } 
	    
	    @DeleteMapping("/")
	    public ResponseEntity<String> deleteDirector(@RequestBody IdContainer myId) {
	    	
	    	Boolean isSuccess = adminService.deleteDirector(myId.getId());		
	    	
	    	if(isSuccess)
	    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	    	else
	    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

	    } 
	     
	    @GetMapping("/search")
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




}