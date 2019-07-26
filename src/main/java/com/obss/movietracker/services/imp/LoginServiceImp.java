package com.obss.movietracker.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.UsersDAO;
import com.obss.movietracker.models.Users;
import com.obss.movietracker.services.LoginService;

@Service
public class LoginServiceImp extends UserServiceImp implements LoginService  {

	@Autowired
	private UsersDAO userRepository;


	
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
