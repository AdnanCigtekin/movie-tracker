package com.obss.movietracker.configurations;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.obss.movietracker.services.UserService;
import com.obss.movietracker.services.imp.UserServiceImp;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("loginService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint  unauthorizedHandler;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Bean
	public JwtAuthenticationFilter  authenticationTokenFilterBean() {
		return new JwtAuthenticationFilter();
	}

	   @Override
	    public void configure(WebSecurity webSecurity) throws Exception
	    {
	        webSecurity
	            .ignoring()
	                // All of Spring Security will ignore the requests
	                .antMatchers(HttpMethod.POST, "/token/**");
	    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
  

        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/token/*", "/h2-console/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
        .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    	
    }
	
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
}
