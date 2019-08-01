package com.obss.movietracker.services.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.UsersDAO;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.LoginService;

@Service
public class LoginServiceImp extends UserServiceImp implements UserDetailsService,LoginService  {

	@Autowired
	private UsersDAO userRepository;


	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> users = userRepository.findMyUserByUsername(username);
		
		
		if(users.isEmpty()){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Users user = users.get(0);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	@Override
	public Boolean login(String username, String password)  {
		
		
		
		List<Users> myUser = userRepository.findMyUser(username, password);

		if(myUser.isEmpty()) {
			return false;
		}else {
			return true;
		}
	
//		try {
//			User myUser = userRepository.findMyUser(username, password).get(0);
//			
//			if(myUser != null) {
//				return true;
//			}
//		}catch(Exception e) {
//			//TODO : Add proper logging
//			e.printStackTrace();
//		}
//		return false;
	}



}
