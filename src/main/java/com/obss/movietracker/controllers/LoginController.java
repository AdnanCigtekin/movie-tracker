package com.obss.movietracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.dto.UserCredentials;



import com.obss.movietracker.services.imp.LoginServiceImp;




@RestController
@RequestMapping("/")
@CrossOrigin
public class LoginController {

    @Autowired
    @Qualifier("loginService")
    private LoginServiceImp loginService;
	


    
    @PostMapping("/")
    public ResponseEntity<?> login(@RequestBody UserCredentials authenticationRequest) throws Exception {
	
    	Boolean isSuccess = loginService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
    	if(isSuccess)
    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    	

		
    	if(isSuccess)
		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	else
		return new ResponseEntity<>("Failed to login", HttpStatus.BAD_REQUEST);
    	
    	
    	

    } 
    

    
}
