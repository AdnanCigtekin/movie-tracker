package com.obss.movietracker.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.obss.movietracker.services.imp.AdminServiceImp;
import com.obss.movietracker.services.imp.LoginServiceImp;
import com.obss.movietracker.services.imp.UserServiceImp;



@EnableWebMvc
@Configuration
public class MovieTrackerAppConfig {

	@Bean
	@Qualifier("loginService")
	public LoginServiceImp getLoginService() {
		return new LoginServiceImp();
	}
	
	@Bean
	@Qualifier("adminService")
	public AdminServiceImp getAdminService() {
		return new AdminServiceImp();
	}
	
	@Bean
	@Qualifier("userService")
	public UserServiceImp getUserService() {
		return new UserServiceImp();
	}
}
