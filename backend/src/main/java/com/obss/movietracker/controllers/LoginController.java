package com.obss.movietracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.configurations.TokenProvider;
import com.obss.movietracker.dto.AuthToken;
import com.obss.movietracker.dto.UserCredentials;
import static com.obss.movietracker.configurations.SecurityConstants.REACT_ORIGIN;


@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider jwtTokenUtil;
	
	//@CrossOrigin(origins = REACT_ORIGIN, maxAge=3600)
	@PostMapping(value = "/")
	public ResponseEntity register(@RequestBody UserCredentials loginUser) throws AuthenticationException{
		final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginUser.getUsername(),
							loginUser.getPassword()
							)
				
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
		
	}
	

	
}
