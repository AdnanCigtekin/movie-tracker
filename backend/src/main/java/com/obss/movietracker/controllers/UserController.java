package com.obss.movietracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.obss.movietracker.dto.UserCreateDTO;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.imp.AdminServiceImp;
import com.obss.movietracker.services.imp.UserServiceImp;
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserServiceImp userService;
	
    @Autowired
    @Qualifier("adminService")
    private AdminServiceImp adminService;
    

    
	@PostConstruct
	public void init() {
		adminService.addAdmin();
	}
    
    
    /*		USER		*/
	@PostMapping("/")
	@Secured({"ROLE_ADMIN"})
	//@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody UserCreateDTO user) {
    	
    	Boolean isSuccess = adminService.addUser(user);		
    	
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @PutMapping("/")
    @Secured({"ROLE_ADMIN"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@RequestBody UserCreateDTO user) {
    
    	Boolean isSuccess = adminService.updateUser(user);		
    	JSONObject msg = new JSONObject();
    	
    	if(isSuccess) {
    		msg.put("message", "Succesfully Updated");
    		return new ResponseEntity<>(msg.toString(), HttpStatus.ACCEPTED);
    	}
    	else {
    		msg.put("message", "Failed to update user");
    		return new ResponseEntity<>(msg.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    } 
    
    @DeleteMapping("/")
    @Secured({"ROLE_ADMIN"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestBody IdContainer myId) {
    	
    	Boolean isSuccess = adminService.deleteUser(myId.getId());		
    	JSONObject msg = new JSONObject();
    	if(isSuccess) {
    		msg.put("message", "Succesfully Deleted");
    		return new ResponseEntity<>(msg.toString(), HttpStatus.ACCEPTED);
    	}
    	else {
    		msg.put("message", "Failed to delete user");
    		return new ResponseEntity<>(msg.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}

    } 
     
    @GetMapping("/search")
    @Secured({"ROLE_ADMIN"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> searchUser(@RequestParam(value="name") String user) {
    	
//    	List<Users> userList = adminService.searchUser(user);		
//    	
//    	//Returning the output
//    	StringBuffer output = new StringBuffer();
//    	output.append("{ \n		'output' : [\n ");
//    	for(Users u : userList) {
//    		output.append("\t\t\t\t\t" + u.toString() + "\n");
//    	}
//    	output.append("\t\t\t\t\t]\n}");
//    	
//    	return new ResponseEntity<>(output.toString(), HttpStatus.ACCEPTED);
//    	
    	
   
    	List<Users> userList =  adminService.searchUser(user);		


    	Integer i = 0;
 
    		JSONObject newObj = new JSONObject();
//    		newObj.put(i.toString(), u.);
    		newObj.put("username", userList.get(0).getUsername());
    		newObj.put("id", userList.get(0).getId());
    		newObj.put("roles", userList.get(0).getRoles());
    	
    		
    	
  
    	//output.append("\t\t\t\t\t]\n}");
    	String output = newObj.toString();
    	return new ResponseEntity<>(output, HttpStatus.OK);
    	
    }   
    
    
    @GetMapping("/search-all")
    @Secured({"ROLE_ADMIN"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAllUser() {
    	
    	JSONObject jos = new JSONObject();
    	List<Users> userList = userService.getAllUsers();		
    	JSONArray joArray = new JSONArray();

    	Integer i = 0;
    	for(Users u : userList) {
    		JSONObject newObj = new JSONObject();
//    		newObj.put(i.toString(), u.);
    		newObj.put("username", u.getUsername());
    		newObj.put("id", u.getId());
    		newObj.put("roles", u.getRoles());
    		joArray.put(newObj);
    		
    	}
    	jos.put("datas", joArray);
    	//output.append("\t\t\t\t\t]\n}");
    	String output = jos.toString();
    	return new ResponseEntity<>(output, HttpStatus.OK);
    	
    }
    
    
    
    @GetMapping("/role")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getUserRole(@RequestParam(value="name") String user) {
    	
    	String myUser = userService.getUserRole(user);		
    	
    	//Returning the output
//    	StringBuffer output = new StringBuffer();
//    	output.append("{ \n		'output' : [\n ");
//    	for(Users u : myUser) {
//    		output.append("\t\t\t\t\t" + u.toString() + "\n");
//    	}
//    	output.append("\t\t\t\t\t]\n}");
    	
    	return new ResponseEntity<>(myUser, HttpStatus.ACCEPTED);
    	
    }
    
   


	
    

    
    
}
