package com.obss.movietracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.configurations.TokenProvider;
import com.obss.movietracker.dto.AuthToken;
import com.obss.movietracker.dto.UserCredentials;



import com.obss.movietracker.services.imp.LoginServiceImp;




//@RestController
//@RequestMapping("/login")
//@CrossOrigin//(origins = "*", maxAge=3600)
//public class LoginController {
//
////    @Autowired
////    @Qualifier("loginService")
////    private LoginServiceImp loginService;
//	
////	@Autowired
////	private AuthenticationManager authenticationManager;
////	
////	@Autowired
////	private TokenProvider jwtTokenUtil;
////
////    
////    @PostMapping("/")
////    public ResponseEntity<?> login(@RequestBody UserCredentials loginUser) throws Exception {
////	
//////    	Boolean isSuccess = loginService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//////		
//////    	if(isSuccess)
//////    		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
//////    	
//////
//////		
//////    	if(isSuccess)
//////		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
//////	else
//////		return new ResponseEntity<>("Failed to login", HttpStatus.BAD_REQUEST);
//////    	
//////    	
////    	
////		final Authentication authentication = authenticationManager.authenticate(
////				new UsernamePasswordAuthenticationToken(
////						loginUser.getUsername(),
////						loginUser.getPassword()
////						)
////			
////			);
////	SecurityContextHolder.getContext().setAuthentication(authentication);
////	
////	final String token = jwtTokenUtil.generateToken(authentication);
////	return ResponseEntity.ok(new AuthToken(token));
////    } 
//    
//    
//   
//
//
//
//    
//
//    
//}
