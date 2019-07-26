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
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.imp.AdminServiceImp;
import com.obss.movietracker.services.imp.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserServiceImp userService;
	
    @Autowired
    @Qualifier("adminService")
    private AdminServiceImp adminService;
    
    /*		USER		*/
	@PostMapping("/")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
    	
    	Boolean isSuccess = adminService.addUser(user);		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @PutMapping("/")
    public ResponseEntity<String> updateUser(@RequestBody Users user) {
    	
    	Boolean isSuccess = adminService.updateUser(user);		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    } 
    
    @DeleteMapping("/")
    public ResponseEntity<String> deleteUser(@RequestBody IdContainer myId) {
    	
    	Boolean isSuccess = adminService.deleteUser(myId.getId());		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    } 
     
    @GetMapping("/search")
    public ResponseEntity<String> searchUser(@RequestParam(value="name") String user) {
    	
    	List<Users> userList = adminService.searchUser(user);		
    	
    	//Returning the output
    	StringBuffer output = new StringBuffer();
    	output.append("{ \n		'output' : [\n ");
    	for(Users u : userList) {
    		output.append("\t\t\t\t\t" + u.toString() + "\n");
    	}
    	output.append("\t\t\t\t\t]\n}");
    	
    	return new ResponseEntity<>(output.toString(), HttpStatus.ACCEPTED);
    	
    }   
    
    
    
    


	
    

    
    
}
